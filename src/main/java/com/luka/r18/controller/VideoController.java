package com.luka.r18.controller;

import com.luka.r18.entity.TagMapEntity;
import com.luka.r18.entity.VideoViewEntity;
import com.luka.r18.entity.response_object.VideoView;
import com.luka.r18.entity.request_object.PageObject;
import com.luka.r18.service.impl.TagMapServiceImpl;
import com.luka.r18.service.impl.VideoViewServiceImpl;
import com.luka.r18.util.CustomUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Controller
@RequestMapping(path = {"/anime"})
public class VideoController {

    @Resource
    VideoViewServiceImpl videoViewService;
    @Resource
    TagMapServiceImpl tagMapService;

    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public String animeViewList(@RequestBody @Valid PageObject pageObject) {
        if (pageObject.getPage() < 0) pageObject.setPage(0);
        PageRequest pageRequest = PageRequest.of(pageObject.getPage(), pageObject.getSize());
        Page<VideoView> animeViewEntities = videoViewService.queryByPage(new VideoViewEntity(), pageRequest, pageObject.getKeyword());
        return CustomUtil.toJson(200, "", animeViewEntities);
    }


    @ResponseBody
    @RequestMapping(path = {"/get"}, method = RequestMethod.GET)
    public String get(@RequestParam String uuid) {

        if (uuid == null) {
            return CustomUtil.toJson(400, "参数异常。");
        }

        PageRequest pageRequest = PageRequest.of(0, 1);
        VideoViewEntity videoViewEntity = new VideoViewEntity();
        videoViewEntity.setUuid(uuid);
        Page<VideoView> animeViewEntities = videoViewService.queryByPage(videoViewEntity, pageRequest, "");

        if (animeViewEntities.getContent().isEmpty()) {
            return CustomUtil.toJson(200, "", null);
        }

        VideoView content = animeViewEntities.getContent().get(0);
        TagMapEntity tagMapEntity = tagMapService.queryTag(uuid);
        Map<String, Object> map = new HashMap<>();
        map.put("detail", content);
        map.put("tag", tagMapEntity);

        return CustomUtil.toJson(200, "", map);
    }


}
