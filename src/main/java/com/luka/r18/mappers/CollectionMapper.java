package com.luka.r18.mappers;

import com.luka.r18.entity.CollectionEntity;
import com.luka.r18.entity.response_object.CollectionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Mapper
public interface CollectionMapper {

    CollectionEntity queryById(Integer id);

    List<CollectionView> queryAllByLimit(@Param("collect") CollectionEntity collectionEntity, @Param("pageable") Pageable pageable);

    long count(CollectionEntity collectionEntity);

    int insert(CollectionEntity collectionEntity);

    int insertBatch(@Param("entities") List<CollectionEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<CollectionEntity> entities);

    int update(CollectionEntity collectionEntity);

    int deleteById(Integer id);

    CollectionEntity selectByEntity(CollectionEntity collectionEntity);

}

