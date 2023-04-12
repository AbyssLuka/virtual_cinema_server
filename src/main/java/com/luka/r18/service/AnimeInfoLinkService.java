package com.luka.r18.service;

import com.luka.r18.entity.AnimeInfoLinkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AnimeInfoLink)表服务接口
 *
 * @author makejava
 * @since 2022-07-24 09:55:54
 */
public interface AnimeInfoLinkService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnimeInfoLinkEntity queryById(Integer id);

    /**
     * 通过ID查询单条数据
     *
     * @param videoUuid anime_file表主键
     * @return 文件路径
     */
    String querySubtitlePath(String videoUuid);

    /**
     * 分页查询
     *
     * @param animeInfoLinkEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AnimeInfoLinkEntity> queryByPage(AnimeInfoLinkEntity animeInfoLinkEntity, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param animeInfoLinkEntity 实例对象
     * @return 实例对象
     */
    AnimeInfoLinkEntity insert(AnimeInfoLinkEntity animeInfoLinkEntity);

    /**
     * 修改数据
     *
     * @param animeInfoLinkEntity 实例对象
     * @return 实例对象
     */
    AnimeInfoLinkEntity update(AnimeInfoLinkEntity animeInfoLinkEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
