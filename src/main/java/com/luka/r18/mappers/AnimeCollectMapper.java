package com.luka.r18.mappers;

import com.luka.r18.entity.AnimeCollectEntity;
import com.luka.r18.entity.response_object.AnimeCollectView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AnimeCollect)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-30 05:03:57
 */
@Mapper
public interface AnimeCollectMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnimeCollectEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param animeCollectEntity 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AnimeCollectView> queryAllByLimit(@Param("animeCollect")AnimeCollectEntity animeCollectEntity, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param animeCollectEntity 查询条件
     * @return 总行数
     */
    long count(AnimeCollectEntity animeCollectEntity);

    /**
     * 新增数据
     *
     * @param animeCollectEntity 实例对象
     * @return 影响行数
     */
    int insert(AnimeCollectEntity animeCollectEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AnimeCollect> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AnimeCollectEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AnimeCollect> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AnimeCollectEntity> entities);

    /**
     * 修改数据
     *
     * @param animeCollectEntity 实例对象
     * @return 影响行数
     */
    int update(AnimeCollectEntity animeCollectEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过实例对象查询数据
     *
     * @param animeCollectEntity 实例对象
     * @return 对象
     */
    AnimeCollectEntity selectByEntity(AnimeCollectEntity animeCollectEntity);

}

