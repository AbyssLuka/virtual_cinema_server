package com.luka.r18.service;

import com.luka.r18.entity.AnimeCollectEntity;
import com.luka.r18.entity.response_object.AnimeCollectView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AnimeCollect)表服务接口
 *
 * @author makejava
 * @since 2022-08-30 05:03:58
 */
public interface AnimeCollectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnimeCollectEntity queryById(Integer id);

    /**
     * 分页查询
     *
     * @param animeCollectEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AnimeCollectView> queryByPage(AnimeCollectEntity animeCollectEntity, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param animeCollectEntity 实例对象
     * @return 实例对象
     */
    AnimeCollectEntity insert(AnimeCollectEntity animeCollectEntity);

    /**
     * 修改数据
     *
     * @param animeCollectEntity 实例对象
     * @return 实例对象
     */
    AnimeCollectEntity update(AnimeCollectEntity animeCollectEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过实例对象查询数据
     *
     * @param animeCollectEntity 实例对象
     * @return 对象
     */
    AnimeCollectEntity selectByEntity(AnimeCollectEntity animeCollectEntity);
}
