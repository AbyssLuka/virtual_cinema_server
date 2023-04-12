package com.luka.r18.service;

import com.luka.r18.entity.AnimeViewEntity;
import com.luka.r18.entity.response_object.AnimeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (AnimeView)表服务接口
 *
 * @author makejava
 * @since 2022-07-15 03:26:49
 */
public interface AnimeViewService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnimeViewEntity queryById(Integer id);

    /**
     * 分页查询
     *
     * @param animeViewEntity 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<AnimeView> queryByPage(AnimeViewEntity animeViewEntity, PageRequest pageRequest, String keyword);

    /**
     * 新增数据
     *
     * @param animeViewEntity 实例对象
     * @return 实例对象
     */
    AnimeViewEntity insert(AnimeViewEntity animeViewEntity);

    /**
     * 修改数据
     *
     * @param animeViewEntity 实例对象
     * @return 实例对象
     */
    AnimeViewEntity update(AnimeViewEntity animeViewEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    /**
     * 判断某条数据是否存在
     *
     * @param uuid 唯一识别码
     * @return 是否成功
     */
    boolean isEmptyByUuid(String uuid);


}
