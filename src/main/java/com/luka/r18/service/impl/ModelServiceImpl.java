package com.luka.r18.service.impl;

import com.luka.r18.entity.ModelEntity;
import com.luka.r18.mappers.ModelMapper;
import com.luka.r18.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Model)表服务实现类
 *
 * @author Luka
 * @since 2024-02-05 04:26:23
 */
@Service("modelService")
public class ModelServiceImpl implements ModelService {
    @Resource
    private ModelMapper modelMapper;

    @Override
    public List<ModelEntity> queryByUserName(String username) {
        return this.modelMapper.queryByUserName(username);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param uuid 主键
     * @return 实例对象
     */
    @Override
    public ModelEntity queryById(String uuid) {
        return this.modelMapper.queryById(uuid);
    }

    /**
     * 分页查询
     *
     * @param model       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<ModelEntity> queryByPage(ModelEntity model, PageRequest pageRequest) {
        long total = this.modelMapper.count(model);
        return new PageImpl<>(this.modelMapper.queryAllByLimit(model, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    @Override
    public ModelEntity insert(ModelEntity model) {
        this.modelMapper.insert(model);
        return model;
    }

    /**
     * 修改数据
     *
     * @param modelEntity 实例对象
     * @return 实例对象
     */
    @Override
    public ModelEntity update(ModelEntity modelEntity) {
        this.modelMapper.update(modelEntity);
        return this.queryById(modelEntity.getUuid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uuid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String uuid) {
        return this.modelMapper.deleteById(uuid) > 0;
    }
}
