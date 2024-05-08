package com.luka.r18.mappers;

import com.luka.r18.entity.ModelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Mapper
public interface ModelMapper {

    List<ModelEntity> queryByUserName(String username);

    ModelEntity queryById(String uuid);

    List<ModelEntity> queryAllByLimit(ModelEntity modelEntity, @Param("pageable") Pageable pageable);

    long count(ModelEntity modelEntity);

    int insert(ModelEntity modelEntity);

    int insertBatch(@Param("entities") List<ModelEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<ModelEntity> entities);

    int update(ModelEntity modelEntity);

    int deleteById(String uuid);

}

