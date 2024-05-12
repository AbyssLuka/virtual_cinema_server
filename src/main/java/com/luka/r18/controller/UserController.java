package com.luka.r18.controller;

import com.google.code.kaptcha.Producer;
import com.luka.r18.entity.request_object.LoginObject;
import com.luka.r18.entity.request_object.SignupObject;
import com.luka.r18.entity.UserEntity;
import com.luka.r18.entity.request_object.UpdatePasswordObject;
import com.luka.r18.entity.response_object.UserInfo;
import com.luka.r18.service.impl.RedisServiceImpl;
import com.luka.r18.service.impl.UserServiceImpl;
import com.luka.r18.util.CustomUtil;
import com.luka.r18.util.JWTToken;
import com.luka.r18.util.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping(path = {"user"})
public class UserController {

    @Resource
    private UserServiceImpl userServiceImpl;
    @Resource
    private RedisServiceImpl redisService;
    @Resource
    private Producer kaptcha;

    @Value("${community.host}")
    private String host;
    @Value("${profile-photo.path}")
    private String profilePhotoPath;

    @RequestMapping(path = {"/login"}, method = RequestMethod.POST)
    public String login(@RequestBody @Validated LoginObject loginObject, HttpServletResponse response) {
        try {
            String username = loginObject.getUsername();
            String password = loginObject.getPassword();
            //查询redis是否存在验证码
            Object code = redisService.get(loginObject.getCode());
            if (code == null) {
                return CustomUtil.toJson(400, "验证码错误或者过期");
            }
            UserEntity userEntity = userServiceImpl.selectUserByName(username);
            password = CustomUtil.md5(password + userEntity.getSalt());
            //创建Token
            String token = TokenUtil.createToken(username, password, userEntity.getUuid());
            //登录验证
            SecurityUtils.getSubject().login(new JWTToken(token));
            response.setHeader("token", token);
            return CustomUtil.toJson(200, "登录成功");
        } catch (UnknownAccountException e) {
            return CustomUtil.toJson(400, "账号或密码错误", e.getMessage());
        } catch (NullPointerException e) {
            return CustomUtil.toJson(400, "账号或密码异常", e.getMessage());
        }
    }

    @RequestMapping(path = {"/signup"}, method = RequestMethod.POST)
    public String register(@RequestBody @Validated SignupObject signupObject) {
        String username = signupObject.getUsername();
        String email = signupObject.getEmail();
        //账号是否已经被注册？
        int count = userServiceImpl.haveNameOrEMali(username, email);
        if (count > 0) {
            return CustomUtil.toJson(10001, "用户名已存在或者邮箱已被注册");
        }
        String tempUuid = (String) redisService.get(email);
        if (tempUuid != null) {
            return CustomUtil.toJson(10002, "激活码未过期");
        }
        //生成激活链接
        String uuid = CustomUtil.getUUID();
        //保存至Redis
        boolean uuidBool = redisService.set(uuid, signupObject, 60 * 30);
        redisService.set(email, uuid, 60 * 30);
        if (!uuidBool) {
            return CustomUtil.toJson(10000, "未知错误 注册失败");
        }
        String url = host.concat("/user/activation/").concat(uuid);
        userServiceImpl.sendActivateMessage(username, url, email);
        return CustomUtil.toJson(200, "激活邮件已发送");
    }

    /**
     * 账号激活
     *
     * @param uuid
     * @return code
     */
    @RequestMapping(path = {"/activation/{uuid}"}, method = RequestMethod.GET)
    public String activation(@PathVariable("uuid") String uuid) {
        SignupObject signupObject = (SignupObject) redisService.get(uuid);
        if (signupObject == null) {
            return CustomUtil.toJson(400, "激活码过期或者不存在");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signupObject.getUsername());
        String random = CustomUtil.getUUID();
        String password = CustomUtil.md5(signupObject.getPassword() + random.split("-")[0]);
        userEntity.setPassword(password);
        userEntity.setSalt(random.split("-")[0]);
        userEntity.setUuid(uuid);
        userEntity.setEmail(signupObject.getEmail());
        userEntity.setProfilePhoto("default.jpg");
        //存入数据库
        int insert = userServiceImpl.insert(userEntity);
        //清除redis中的缓存数据
        String email = signupObject.getEmail();
        redisService.del(email, uuid);
        if (insert > 0) {
            return CustomUtil.toJson(200, "激活成功");
        } else {
            return CustomUtil.toJson(200, "激活失败");
        }
    }

    @RequestMapping(path = {"/profile_photo"}, method = RequestMethod.GET)
    public void profilePhoto(HttpServletResponse response, HttpServletRequest request) {
        String token = request.getHeader("token");
        try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            String profile;
            if (token == null) {
                profile = profilePhotoPath.concat("/default.jpg");
            } else {
                String username = TokenUtil.getTokenClaim(token, "username");
                UserEntity userEntity = userServiceImpl.selectUserByName(username);
                if (userEntity == null) {
                    profile = profilePhotoPath.concat("/default.jpg");
                } else {
                    profile = profilePhotoPath.concat("/").concat(userEntity.getProfilePhoto());
                }
            }
            byte[] buffer = CustomUtil.getFile(profile);
            assert buffer != null;
            outputStream.write(buffer);
        } catch (Exception ignored) {
        }
    }

    @RequestMapping(path = {"/info"}, method = RequestMethod.GET)
    public String userInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userName = TokenUtil.getTokenClaim(token, "username");
        if (userName == null) {
            return CustomUtil.toJson(400, "Token无效");
        }
        UserInfo userInfo = userServiceImpl.selectUserInfoByName(userName);
        if (userInfo == null) {
            return CustomUtil.toJson(400, "没有找到用户");
        }
        return CustomUtil.toJson(200, "成功", userInfo);
    }

    @RequestMapping(path = {"/update_password"}, method = RequestMethod.POST)
    public String updatePassword(@RequestBody UpdatePasswordObject update, HttpServletRequest request, HttpServletResponse response) {
        if (update == null) {
            return CustomUtil.toJson(400, "参数错误");
        }
        String username = TokenUtil.getTokenClaim(request.getHeader("token"), "username");

        UserEntity userEntity = userServiceImpl.selectUserByName(username);

        String oldPassword = CustomUtil.md5(update.getOldPassword() + userEntity.getSalt());
        if (!oldPassword.equals(userEntity.getPassword())){
            return CustomUtil.toJson(-1, "失败");
        }

        String salt = CustomUtil.getUUID().split("-")[0];
        String newPassword = CustomUtil.md5(update.getNewPassword() + salt);
        userEntity.setPassword(newPassword);
        userEntity.setSalt(salt);
        userServiceImpl.update(userEntity);

        String token = TokenUtil.createToken(username, newPassword, userEntity.getUuid());                                                       //生成新的token
        response.setHeader("token", token);

        return CustomUtil.toJson(200, "成功");
    }

    @RequestMapping(path = {"/kaptcha"}, method = RequestMethod.GET)
    public void kaptcha(HttpServletResponse response) {
        String text = kaptcha.createText();
        BufferedImage image = kaptcha.createImage(text);
        redisService.set(text, text, 60 * 5);
        response.setContentType("image/png");
        try (ServletOutputStream outputStream = response.getOutputStream();) {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException ignored) {
        }
    }

    @RequestMapping(path = {"/sendEmailCode"}, method = RequestMethod.POST)
    public String sendEmailCode(@RequestBody SignupObject signupObject) {
        String email = signupObject.getEmail();
        String username = signupObject.getUsername();
        String uuid = (String) redisService.get(email);
        if (uuid == null) {
            uuid = CustomUtil.getUUID();
            redisService.set(email, uuid, 60 * 5);
        }
        String url = host.concat("/user/activation/").concat(uuid);
        userServiceImpl.sendActivateMessage(username, url, email);
        return CustomUtil.toJson(10003, "失败");
    }

    @RequestMapping(path = {"/uploadProfilePhoto"}, method = RequestMethod.GET)
    public void uploadProfilePhoto(HttpServletResponse response) {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        System.out.println(principal.toString());
    }
}
