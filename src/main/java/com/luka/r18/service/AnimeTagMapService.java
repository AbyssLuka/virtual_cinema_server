package com.luka.r18.service;

import com.luka.r18.entity.AnimeTagMapEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AnimeTagMap)表服务接口
 *
 * @author makejava
 * @since 2022-09-21 00:38:38
 */
public interface AnimeTagMapService {


    /**
     * 通过ID查询单条数据
     *
     * @param animeViewUuid 主键
     * @return 实例对象
     */
    AnimeTagMapEntity queryAnimeTag(String animeViewUuid);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnimeTagMapEntity queryById(Integer id);

    /**
     * 分页查询
     *
     * @param animeTagMapEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AnimeTagMapEntity> queryByPage(AnimeTagMapEntity animeTagMapEntity, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param animeTagMapEntity 实例对象
     * @return 实例对象
     */
    AnimeTagMapEntity insert(AnimeTagMapEntity animeTagMapEntity);

    /**
     * 修改数据
     *
     * @param animeTagMapEntity 实例对象
     * @return 实例对象
     */
    AnimeTagMapEntity update(AnimeTagMapEntity animeTagMapEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
