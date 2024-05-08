package com.luka.r18.controller;

import com.luka.r18.entity.TagMapEntity;
import com.luka.r18.entity.request_object.TagMap;
import com.luka.r18.service.impl.TagMapServiceImpl;
import com.luka.r18.util.CustomUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("TagMap")
public class TagMapController {

    @Resource
    private TagMapServiceImpl tagMapService;

}

