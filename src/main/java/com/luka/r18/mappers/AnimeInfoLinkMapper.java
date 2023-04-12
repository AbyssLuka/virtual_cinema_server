package com.luka.r18.mappers;

import com.luka.r18.entity.AnimeInfoLinkEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AnimeInfoLink)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-24 09:55:52
 */
@Mapper
public interface AnimeInfoLinkMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnimeInfoLinkEntity queryById(Integer id);

    /**
     * 通过ID查询单条数据
     *
     * @param videoUuid anime_file表主键
     * @return 文件路径
     */
    String querySubtitlePath(String videoUuid);

    /**
     * 查询指定行数据
     *
     * @param animeInfoLinkEntity 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AnimeInfoLinkEntity> queryAllByLimit(AnimeInfoLinkEntity animeInfoLinkEntity, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param animeInfoLinkEntity 查询条件
     * @return 总行数
     */
    long count(AnimeInfoLinkEntity animeInfoLinkEntity);

    /**
     * 新增数据
     *
     * @param animeInfoLinkEntity 实例对象
     * @return 影响行数
     */
    int insert(AnimeInfoLinkEntity animeInfoLinkEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AnimeInfoLink> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AnimeInfoLinkEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AnimeInfoLink> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AnimeInfoLinkEntity> entities);

    /**
     * 修改数据
     *
     * @param animeInfoLinkEntity 实例对象
     * @return 影响行数
     */
    int update(AnimeInfoLinkEntity animeInfoLinkEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

