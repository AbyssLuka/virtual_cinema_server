package com.luka.r18.service;

import com.luka.r18.entity.AnimeTagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AnimeTag)表服务接口
 *
 * @author makejava
 * @since 2022-09-21 00:25:09
 */
public interface AnimeTagService {

    /**
     * 通过ID查询单条数据
     *
     * @param uuid 主键
     * @return 实例对象
     */
    AnimeTagEntity queryByUuid(String uuid);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnimeTagEntity queryById(Integer id);

    /**
     * 分页查询
     *
     * @param animeTagEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AnimeTagEntity> queryByPage(AnimeTagEntity animeTagEntity, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param animeTagEntity 实例对象
     * @return 实例对象
     */
    AnimeTagEntity insert(AnimeTagEntity animeTagEntity);

    /**
     * 修改数据
     *
     * @param animeTagEntity 实例对象
     * @return 实例对象
     */
    AnimeTagEntity update(AnimeTagEntity animeTagEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
