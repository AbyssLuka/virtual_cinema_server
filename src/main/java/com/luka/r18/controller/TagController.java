package com.luka.r18.controller;

import com.luka.r18.entity.TagEntity;
import com.luka.r18.service.impl.TagServiceImpl;
import com.luka.r18.util.CustomUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("tag")
public class TagController {

    @Resource
    TagServiceImpl tagService;

    @RequestMapping(path = {"add"})
    public String add(String tagName){
        TagEntity tagEntity = new TagEntity();
        tagEntity.setUuid(CustomUtil.getUUID());
        tagEntity.setTagName(tagName);
        tagEntity.setInfo("");
        tagEntity.setCreateTime(new Date());
        tagEntity = tagService.insert(tagEntity);
        if (tagEntity !=null){
            return CustomUtil.toJson(200,"");
        }else {
            return CustomUtil.toJson(400,"error");
        }
    }
}

