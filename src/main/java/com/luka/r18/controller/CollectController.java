package com.luka.r18.controller;

import com.luka.r18.entity.AnimeCollectEntity;
import com.luka.r18.entity.UserDataEntity;
import com.luka.r18.entity.request_object.PageObject;
import com.luka.r18.entity.response_object.AnimeCollectView;
import com.luka.r18.service.impl.AnimeCollectServiceImpl;
import com.luka.r18.service.impl.AnimeViewServiceImpl;
import com.luka.r18.service.impl.UserDataServiceImpl;
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
public class CollectController {

    @Resource
    AnimeViewServiceImpl animeViewService;

    @Resource
    UserDataServiceImpl userDataService;

    @Resource
    AnimeCollectServiceImpl animeCollectService;

    @ResponseBody
    @RequestMapping(path = {"/add/{uuid}"}, method = RequestMethod.GET)
    public String AddCollect(@PathVariable String uuid, HttpServletRequest request) {
        String token = request.getHeader("token");

        String username = TokenUtil.getTokenClaim(token, "username");
        UserDataEntity userDataEntity = userDataService.selectUserByName(username);

        AnimeCollectEntity collect = new AnimeCollectEntity();
        collect.setUuid(CustomUtil.getUUID());
        collect.setUserUuid(userDataEntity.getUuid());
        if (animeViewService.isEmptyByUuid(uuid)) {
            collect.setViewUuid(uuid);
        }else {
            return CustomUtil.toJson(520, "添加失败，未知错误。");
        }
        collect.setCreateTime(new Date());
        AnimeCollectEntity insert = animeCollectService.insert(collect);
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
        UserDataEntity userDataEntity = userDataService.selectUserByName(username);

        AnimeCollectEntity collect = new AnimeCollectEntity();
        collect.setUserUuid(userDataEntity.getUuid());
        collect.setViewUuid(uuid);

        AnimeCollectEntity entity = animeCollectService.selectByEntity(collect);

        if (entity == null) return CustomUtil.toJson(-1, "不存在的数据。");

        boolean state = animeCollectService.deleteById(entity.getId());
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
        UserDataEntity userDataEntity = userDataService.selectUserByName(username);
        if (userDataEntity == null) return CustomUtil.toJson(520, "用户不存在。") ;
        AnimeCollectEntity collect = new AnimeCollectEntity();
        collect.setUserUuid(userDataEntity.getUuid());

        PageRequest pageRequest = PageRequest.of(pageObject.getPage(), pageObject.getSize());
        Page<AnimeCollectView> entity = animeCollectService.queryByPage(collect, pageRequest);

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
        AnimeCollectEntity collectEntity = new AnimeCollectEntity();
        collectEntity.setUserUuid(userUuid);
        collectEntity.setViewUuid(viewUuid);
        collectEntity = animeCollectService.selectByEntity(collectEntity);
        if (collectEntity == null) {
            return CustomUtil.toJson(200, "", false);
        } else {
            return CustomUtil.toJson(200, "", true);
        }
    }

}
