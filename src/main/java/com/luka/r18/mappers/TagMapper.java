package com.luka.r18.mappers;

import com.luka.r18.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Mapper
public interface TagMapper {

    TagEntity queryByUuid(String uuid);

    TagEntity queryById(Integer id);

    List<TagEntity> queryAllByLimit(TagEntity tagEntity, @Param("pageable") Pageable pageable);

    long count(TagEntity tagEntity);

    int insert(TagEntity tagEntity);

    int insertBatch(@Param("entities") List<TagEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<TagEntity> entities);

    int update(TagEntity tagEntity);

    int deleteById(Integer id);

}

