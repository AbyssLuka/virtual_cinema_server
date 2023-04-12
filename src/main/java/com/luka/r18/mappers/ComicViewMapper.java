package com.luka.r18.mappers;

import com.luka.r18.entity.ComicViewEntity;
import com.luka.r18.entity.response_object.ComicView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (ComicView)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-09 16:27:38
 */
@Mapper
public interface ComicViewMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param uuid 主键
     * @return 实例对象
     */
    ComicViewEntity queryById(String uuid);

    /**
     * 查询指定行数据
     *
     * @param comicViewEntity 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ComicView> queryAllByLimit(@Param("ComicViewEntity") ComicViewEntity comicViewEntity, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param comicViewEntity 查询条件
     * @return 总行数
     */
    long count(@Param("ComicViewEntity")ComicViewEntity comicViewEntity);

    /**
     * 新增数据
     *
     * @param comicViewEntity 实例对象
     * @return 影响行数
     */
    int insert(@Param("entity")ComicViewEntity comicViewEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ComicView> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ComicViewEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ComicView> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ComicViewEntity> entities);

    /**
     * 修改数据
     *
     * @param comicViewEntity 实例对象
     * @return 影响行数
     */
    int update(@Param("ComicViewEntity")ComicViewEntity comicViewEntity);

    /**
     * 通过主键删除数据
     *
     * @param uuid 主键
     * @return 影响行数
     */
    int deleteById(String uuid);

}

