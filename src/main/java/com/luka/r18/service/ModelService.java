package com.luka.r18.service;

import com.luka.r18.entity.ModelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Model)表服务接口
 *
 * @author Luka
 * @since 2024-02-05 04:26:23
 */
public interface ModelService {
    /**
     * 通过username查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    List<ModelEntity> queryByUserName(String username);

    /**
     * 通过ID查询单条数据
     *
     * @param uuid 主键
     * @return 实例对象
     */
    ModelEntity queryById(String uuid);

    /**
     * 分页查询
     *
     * @param model 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ModelEntity> queryByPage(ModelEntity model, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    ModelEntity insert(ModelEntity model);

    /**
     * 修改数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    ModelEntity update(ModelEntity model);

    /**
     * 通过主键删除数据
     *
     * @param uuid 主键
     * @return 是否成功
     */
    boolean deleteById(String uuid);

}
