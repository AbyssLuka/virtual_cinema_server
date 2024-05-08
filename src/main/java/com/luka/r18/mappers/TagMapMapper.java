package com.luka.r18.mappers;

import com.luka.r18.entity.TagMapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Mapper
public interface TagMapMapper {

    TagMapEntity queryTag(String viewUuid);

    TagMapEntity queryById(Integer id);

    List<TagMapEntity> queryAllByLimit(TagMapEntity tagMapEntity, @Param("pageable") Pageable pageable);

    long count(TagMapEntity tagMapEntity);

    int insert(TagMapEntity tagMapEntity);

    int insertBatch(@Param("entities") List<TagMapEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<TagMapEntity> entities);

    int update(TagMapEntity tagMapEntity);

    int deleteById(Integer id);

}

