package com.luka.r18.mappers;

import com.luka.r18.entity.AnimeTagMapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AnimeTagMap)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-21 00:38:33
 */
@Mapper
public interface AnimeTagMapMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param animeViewUuid 主键
     * @return 实例对象
     */
    AnimeTagMapEntity queryAnimeTag(String animeViewUuid);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnimeTagMapEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param animeTagMapEntity 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AnimeTagMapEntity> queryAllByLimit(AnimeTagMapEntity animeTagMapEntity, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param animeTagMapEntity 查询条件
     * @return 总行数
     */
    long count(AnimeTagMapEntity animeTagMapEntity);

    /**
     * 新增数据
     *
     * @param animeTagMapEntity 实例对象
     * @return 影响行数
     */
    int insert(AnimeTagMapEntity animeTagMapEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AnimeTagMap> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AnimeTagMapEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AnimeTagMap> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AnimeTagMapEntity> entities);

    /**
     * 修改数据
     *
     * @param animeTagMapEntity 实例对象
     * @return 影响行数
     */
    int update(AnimeTagMapEntity animeTagMapEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

