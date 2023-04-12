package com.luka.r18.controller;

import com.google.code.kaptcha.Producer;
import com.luka.r18.entity.request_object.LoginObject;
import com.luka.r18.entity.request_object.SignupObject;
import com.luka.r18.entity.UserDataEntity;
import com.luka.r18.entity.response_object.UserInfo;
import com.luka.r18.service.impl.RedisServiceImpl;
import com.luka.r18.service.impl.UserDataServiceImpl;
import com.luka.r18.util.CustomUtil;
import com.luka.r18.util.JWTToken;
import com.luka.r18.util.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping(path = {"user"})
public class UserController {

    @Resource
    private UserDataServiceImpl userDataServiceImpl;

    @Resource
    private RedisServiceImpl redisService;

    @Resource
    Producer kaptcha;

    @Value("${community.host}")
    private String host;
    @Value("${profile-photo.path}")
    private String profilePhotoPath;

    @ResponseBody
    @RequestMapping(path = {"/login"}, method = RequestMethod.POST)
    public String login(@RequestBody LoginObject loginObject, HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = loginObject.getUsername();
            String password = loginObject.getPassword();

            Object code = redisService.get(loginObject.getCode());                                              //查询redis是否存在验证码
            if (code == null) {
                return CustomUtil.toJson(400, "验证码错误或者过期");
            }

            UserDataEntity userDataEntity = userDataServiceImpl.selectUserByName(username);                             //查询账号是否存在
            password = CustomUtil.md5(password + userDataEntity.getSalt());                                        //密文密码
            String token = TokenUtil.createToken(username, password, userDataEntity.getUuid());                         //创建Token
            SecurityUtils.getSubject().login(new JWTToken(token));                                                      //登录验证
            response.setHeader("token", token);                                                                      //Token存入请求头
            return CustomUtil.toJson(200, "登录成功");
        } catch (UnknownAccountException e) {
            return CustomUtil.toJson(400, "账号或密码错误", e);
        } catch (NullPointerException e) {
            return CustomUtil.toJson(400, "账号或密码异常", e);
        }
    }

    /**
     * 用户类
     *
     * @param signupObject 注册信息
     * @return code
     */
    @ResponseBody
    @RequestMapping(path = {"/signup"}, method = RequestMethod.POST)
    public String register(@RequestBody SignupObject signupObject) {
        String username = signupObject.getUsername();
        String email = signupObject.getEmail();
        //账号是否已经被注册？
        int count = userDataServiceImpl.haveNameOrEMali(username, email);
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
        boolean uuidBool = redisService.set(uuid, signupObject, 18000000);

        redisService.set(email, uuid, 600000);

        if (!uuidBool) {
            return CustomUtil.toJson(10000, "未知错误 注册失败");
        }

        String url = host.concat("/user/activation/").concat(uuid);
        userDataServiceImpl.sendActivateMessage(username, url, email);

        return CustomUtil.toJson(200, "激活邮件已发送");
    }

    /**
     * 账号激活
     *
     * @param uuid
     * @return code
     */
    @ResponseBody
    @RequestMapping(path = {"/activation/{uuid}"}, method = RequestMethod.GET)
    public String activation(@PathVariable("uuid") String uuid) {
        SignupObject signupObject = (SignupObject) redisService.get(uuid);
        if (signupObject == null) {
            return CustomUtil.toJson(400, "激活码过期或者不存在");
        }
        UserDataEntity userDataEntity = new UserDataEntity();
        userDataEntity.setUsername(signupObject.getUsername());
        String random = CustomUtil.getUUID();
        String password = CustomUtil.md5(signupObject.getPassword() + random.split("-")[0]);
        userDataEntity.setPassword(password);
        userDataEntity.setSalt(random.split("-")[0]);
        userDataEntity.setUuid(uuid);
        userDataEntity.setEmail(signupObject.getEmail());
        userDataEntity.setProfilePhoto("default.jpg");
        //存入数据库
        userDataServiceImpl.insert(userDataEntity);
        //清除redis中的缓存数据
        String email = signupObject.getEmail();
        redisService.del(email, uuid);
        return CustomUtil.toJson(200, "激活成功");
    }

    @ResponseBody
    @RequestMapping(path = {"/profile_photo"}, method = RequestMethod.GET)
    public void profilePhoto(HttpServletResponse response, HttpServletRequest request) {
        String token = request.getHeader("token");
        try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            String profile;
            if (token == null) {
                profile = profilePhotoPath.concat("/default.jpg");
            } else {
                String username = TokenUtil.getTokenClaim(token, "username");
                UserDataEntity userDataEntity = userDataServiceImpl.selectUserByName(username);
                if (userDataEntity == null) {
                    profile = profilePhotoPath.concat("/default.jpg");
                } else {
                    profile = profilePhotoPath.concat("/").concat(userDataEntity.getProfilePhoto());
                }
            }
            byte[] buffer = CustomUtil.getFile(profile);
            assert buffer != null;
            outputStream.write(buffer);
        } catch (Exception ignored) {
        }
    }

    @ResponseBody
    @RequestMapping(path = {"/info"}, method = RequestMethod.GET)
    public String userInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userName = TokenUtil.getTokenClaim(token, "username");
        if (userName == null) {
            return CustomUtil.toJson(400, "Token无效");
        }
        UserInfo userInfo = userDataServiceImpl.selectUserInfoByName(userName);
        if (userInfo == null) {
            return CustomUtil.toJson(400, "没有找到用户");
        }
        return CustomUtil.toJson(200, "成功", userInfo);
    }

    @ResponseBody
    @RequestMapping(path = {"/update_password"}, method = RequestMethod.POST)
    public String updatePassword(@RequestBody LoginObject update, HttpServletRequest request, HttpServletResponse response) {
        if (update == null) {
            return CustomUtil.toJson(400, "参数错误");
        }
        String username = TokenUtil.getTokenClaim(request.getHeader("token"), "username");

        UserDataEntity userDataEntity = userDataServiceImpl.selectUserByName(username);
        userDataEntity.setUsername(username);
        String salt = CustomUtil.getUUID().split("-")[0];
        String password = CustomUtil.md5(update.getPassword() + salt);
        userDataEntity.setPassword(password);
        userDataEntity.setSalt(salt);
        userDataServiceImpl.update(userDataEntity);                                                                     //更新

        String token = TokenUtil.createToken(username, password, userDataEntity.getUuid());                                                       //生成新的token
        response.setHeader("token", token);

        return CustomUtil.toJson(200, "成功");
    }

    @ResponseBody
    @RequestMapping(path = {"/kaptcha"}, method = RequestMethod.GET)
    public void kaptcha(HttpServletResponse response) {
        String text = kaptcha.createText();
        BufferedImage image = kaptcha.createImage(text);
        redisService.set(text, text, 60000);
        response.setContentType("image/png");
        try (ServletOutputStream outputStream = response.getOutputStream();) {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException ignored) {
        }
    }

    @ResponseBody
    @RequestMapping(path = {"/sendEmailCode"}, method = RequestMethod.POST)
    public String sendEmailCode(@RequestBody SignupObject signupObject) {
        String email = signupObject.getEmail();
        String username = signupObject.getUsername();
        String uuid = (String) redisService.get(email);
        if (uuid == null) {
            uuid = CustomUtil.getUUID();
            redisService.set(email, uuid);
        }
        String url = host.concat("/user/activation/").concat(uuid);
        userDataServiceImpl.sendActivateMessage(username, url, email);
        return CustomUtil.toJson(10003, "失败");
    }

    @ResponseBody
    @RequestMapping(path = {"/uploadProfilePhoto"}, method = RequestMethod.GET)
    public void uploadProfilePhoto(HttpServletResponse response) {
        UserDataEntity principal = (UserDataEntity) SecurityUtils.getSubject().getPrincipal();
        System.out.println(principal.getUsername());
    }
}
