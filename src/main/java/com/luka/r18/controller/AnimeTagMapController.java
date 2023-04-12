package com.luka.r18.controller;

import com.luka.r18.entity.AnimeTagMapEntity;
import com.luka.r18.entity.request_object.AnimeTagMap;
import com.luka.r18.service.impl.AnimeTagMapServiceImpl;
import com.luka.r18.util.CustomUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (AnimeTagMap)表控制层
 *
 * @author makejava
 * @since 2022-09-21 00:38:32
 */
@RestController
@RequestMapping("TagMap")
public class AnimeTagMapController {
    /**
     * 服务对象
     */
    @Resource
    private AnimeTagMapServiceImpl animeTagMapService;


    @RequestMapping(path = {"add"}, method = RequestMethod.POST)
    public String add(@RequestBody AnimeTagMap animeTagMap) {
        AnimeTagMapEntity tagMap = new AnimeTagMapEntity();
        tagMap.setAnimeViewUuid(animeTagMap.getAnimeUuid());
        tagMap.setTagUuid(animeTagMap.getTagUuid());
        AnimeTagMapEntity insert = animeTagMapService.insert(tagMap);
        if (insert != null) {
            return CustomUtil.toJson(200, "");
        } else {
            return CustomUtil.toJson(400, "");
        }
    }


}

