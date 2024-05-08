package com.luka.r18.service;

import com.luka.r18.entity.ModelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ModelService {

    List<ModelEntity> queryByUserName(String username);

    ModelEntity queryById(String uuid);

    Page<ModelEntity> queryByPage(ModelEntity model, PageRequest pageRequest);

    ModelEntity insert(ModelEntity model);

    ModelEntity update(ModelEntity model);

    boolean deleteById(String uuid);

}
