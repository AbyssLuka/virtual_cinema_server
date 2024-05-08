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

@Service("modelService")
public class ModelServiceImpl implements ModelService {
    @Resource
    private ModelMapper modelMapper;

    @Override
    public List<ModelEntity> queryByUserName(String username) {
        return this.modelMapper.queryByUserName(username);
    }

    @Override
    public ModelEntity queryById(String uuid) {
        return this.modelMapper.queryById(uuid);
    }

    @Override
    public Page<ModelEntity> queryByPage(ModelEntity model, PageRequest pageRequest) {
        long total = this.modelMapper.count(model);
        return new PageImpl<>(this.modelMapper.queryAllByLimit(model, pageRequest), pageRequest, total);
    }

    @Override
    public ModelEntity insert(ModelEntity model) {
        this.modelMapper.insert(model);
        return model;
    }

    @Override
    public ModelEntity update(ModelEntity modelEntity) {
        this.modelMapper.update(modelEntity);
        return this.queryById(modelEntity.getUuid());
    }

    @Override
    public boolean deleteById(String uuid) {
        return this.modelMapper.deleteById(uuid) > 0;
    }
}
