package com.luka.r18.controller;

import com.luka.r18.entity.ComicViewEntity;
import com.luka.r18.entity.response_object.ComicView;
import com.luka.r18.entity.request_object.PageObject;
import com.luka.r18.service.ComicViewService;
import com.luka.r18.util.CustomUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/comic")
public class ComicController {

    @Resource
    private ComicViewService comicViewService;

    @ResponseBody
    @RequestMapping(path = {"/list"}, method = RequestMethod.POST)
    public String comic(@RequestBody @Valid PageObject pageObject) {
        PageRequest of = PageRequest.of(pageObject.getPage(), pageObject.getSize());
        Page<ComicView> comicViewEntities = comicViewService.queryByPage(new ComicViewEntity(), of);
        return CustomUtil.toJson(200, "", comicViewEntities);
    }

    @ResponseBody
    @RequestMapping(path = {"/{uuid}"}, method = RequestMethod.GET)
    public String get(@PathVariable("uuid") String uuid) {
        PageRequest of = PageRequest.of(0, 1);
        ComicViewEntity comicViewEntity = new ComicViewEntity();
        comicViewEntity.setUuid(uuid);
        Page<ComicView> comicViewEntities = comicViewService.queryByPage(comicViewEntity, of);
        ComicView comicView = comicViewEntities.getContent().get(0);
        return CustomUtil.toJson(200, "", comicView);
    }
}
