package com.luka.r18.service;

import com.luka.r18.entity.ComicViewEntity;
import com.luka.r18.entity.response_object.ComicView;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (ComicView)表服务接口
 *
 * @author makejava
 * @since 2022-11-09 16:27:39
 */
public interface ComicViewService {

    /**
     * 通过ID查询单条数据
     *
     * @param uuid 主键
     * @return 实例对象
     */
    ComicViewEntity queryById(String uuid);

    /**
     * 分页查询
     *
     * @param comicViewEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ComicView> queryByPage(ComicViewEntity comicViewEntity, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param comicViewEntity 实例对象
     * @return 实例对象
     */
    ComicViewEntity insert( ComicViewEntity comicViewEntity);

    /**
     * 修改数据
     *
     * @param comicViewEntity 实例对象
     * @return 实例对象
     */
    ComicViewEntity update(ComicViewEntity comicViewEntity);

    /**
     * 通过主键删除数据
     *
     * @param uuid 主键
     * @return 是否成功
     */
    boolean deleteById(String uuid);

}
