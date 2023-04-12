package com.luka.r18.controller;

import com.luka.r18.entity.AnimeTagMapEntity;
import com.luka.r18.entity.AnimeViewEntity;
import com.luka.r18.entity.response_object.AnimeView;
import com.luka.r18.entity.request_object.PageObject;
import com.luka.r18.service.impl.AnimeTagMapServiceImpl;
import com.luka.r18.service.impl.AnimeViewServiceImpl;
import com.luka.r18.util.CustomUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@Controller
@RequestMapping(path = {"/anime"})
public class AnimeController {

    @Resource
    AnimeViewServiceImpl animeViewService;

    @Resource
    AnimeTagMapServiceImpl animeTagMapService;

    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public String animeViewList(@RequestBody PageObject pageObject) {

        if (pageObject.getPage() < 0) pageObject.setPage(0);

        PageRequest pageRequest = PageRequest.of(pageObject.getPage(), pageObject.getSize());

        Page<AnimeView> animeViewEntities = animeViewService.queryByPage(new AnimeViewEntity(), pageRequest, pageObject.getKeyword());

        return CustomUtil.toJson(200, "", animeViewEntities);
    }


    @ResponseBody
    @RequestMapping(path = {"/get"}, method = RequestMethod.GET)
    public String get(@RequestParam String uuid) {

        if (uuid == null) {
            return CustomUtil.toJson(400, "参数异常。");
        }

        PageRequest pageRequest = PageRequest.of(0, 1);
        AnimeViewEntity animeViewEntity = new AnimeViewEntity();
        animeViewEntity.setUuid(uuid);
        Page<AnimeView> animeViewEntities = animeViewService.queryByPage(animeViewEntity, pageRequest, "");

        if (animeViewEntities.getContent().size() == 0) {
            return CustomUtil.toJson(200, "", null);
        }

        AnimeView content = animeViewEntities.getContent().get(0);
        AnimeTagMapEntity tagMapEntity = animeTagMapService.queryAnimeTag(uuid);
        Map<String, Object> map = new HashMap<>();
        map.put("detail", content);
        map.put("tag", tagMapEntity);

        return CustomUtil.toJson(200, "", map);
    }


}
