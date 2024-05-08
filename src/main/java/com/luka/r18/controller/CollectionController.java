package com.luka.r18.controller;

import com.luka.r18.entity.CollectionEntity;
import com.luka.r18.entity.UserEntity;
import com.luka.r18.entity.request_object.PageObject;
import com.luka.r18.entity.response_object.CollectionView;
import com.luka.r18.service.impl.CollectionServiceImpl;
import com.luka.r18.service.impl.VideoViewServiceImpl;
import com.luka.r18.service.impl.UserServiceImpl;
import com.luka.r18.util.CustomUtil;
import com.luka.r18.util.TokenUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(path = {"/collect"})
public class CollectionController {

    @Resource
    VideoViewServiceImpl videoViewService;

    @Resource
    UserServiceImpl userService;

    @Resource
    CollectionServiceImpl collectionService;

    @ResponseBody
    @RequestMapping(path = {"/add/{uuid}"}, method = RequestMethod.GET)
    public String AddCollect(@PathVariable String uuid, HttpServletRequest request) {
        String token = request.getHeader("token");

        String username = TokenUtil.getTokenClaim(token, "username");
        UserEntity userEntity = userService.selectUserByName(username);

        CollectionEntity collect = new CollectionEntity();
        collect.setUuid(CustomUtil.getUUID());
        collect.setUserUuid(userEntity.getUuid());
        if (videoViewService.isEmptyByUuid(uuid)) {
            collect.setViewUuid(uuid);
        }else {
            return CustomUtil.toJson(520, "添加失败，未知错误。");
        }
        collect.setCreateTime(new Date());
        CollectionEntity insert = collectionService.insert(collect);
        if (insert != null){
            return CustomUtil.toJson(200, "添加成功。");
        }else {
            return CustomUtil.toJson(520, "添加失败，未知错误。");
        }

    }


    @ResponseBody
    @RequestMapping(path = {"/remove"}, method = RequestMethod.GET)
    public String removeCollect(@RequestParam String uuid, HttpServletRequest request) {

        String token = request.getHeader("token");
        String username = TokenUtil.getTokenClaim(token, "username");
        UserEntity userEntity = userService.selectUserByName(username);

        CollectionEntity collect = new CollectionEntity();
        collect.setUserUuid(userEntity.getUuid());
        collect.setViewUuid(uuid);

        CollectionEntity entity = collectionService.selectByEntity(collect);

        if (entity == null) return CustomUtil.toJson(-1, "不存在的数据。");

        boolean state = collectionService.deleteById(entity.getId());
        if (state) {
            return CustomUtil.toJson(200, "删除成功。");
        } else {
            return CustomUtil.toJson(520, "删除失败，未知错误。");
        }
    }

    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public String list(@Valid PageObject pageObject, HttpServletRequest request) {
        String token = request.getHeader("token");

        String username = TokenUtil.getTokenClaim(token, "username");
        UserEntity userEntity = userService.selectUserByName(username);
        if (userEntity == null) return CustomUtil.toJson(520, "用户不存在。") ;
        CollectionEntity collect = new CollectionEntity();
        collect.setUserUuid(userEntity.getUuid());

        PageRequest pageRequest = PageRequest.of(pageObject.getPage(), pageObject.getSize());
        Page<CollectionView> entity = collectionService.queryByPage(collect, pageRequest);

        if (entity == null) {
            return CustomUtil.toJson(520, "不存在的数据。");
        }else {
            return CustomUtil.toJson(200, "", entity);
        }
    }


    @ResponseBody
    @RequestMapping(path = {"/ishave/{viewUuid}"}, method = RequestMethod.GET)
    public String isHave(HttpServletRequest request, @PathVariable("viewUuid") String viewUuid) {
        String token = request.getHeader("token");
        String userUuid = TokenUtil.getTokenClaim(token, "userUuid");
        CollectionEntity collectEntity = new CollectionEntity();
        collectEntity.setUserUuid(userUuid);
        collectEntity.setViewUuid(viewUuid);
        collectEntity = collectionService.selectByEntity(collectEntity);
        if (collectEntity == null) {
            return CustomUtil.toJson(200, "", false);
        } else {
            return CustomUtil.toJson(200, "", true);
        }
    }

}
