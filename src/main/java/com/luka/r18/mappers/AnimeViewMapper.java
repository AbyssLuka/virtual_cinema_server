package com.luka.r18.mappers;

import com.luka.r18.entity.AnimeViewEntity;
import com.luka.r18.entity.response_object.AnimeView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (AnimeView)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-15 03:26:47
 */
@Mapper
public interface AnimeViewMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AnimeViewEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param animeViewEntity 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AnimeView> queryAllByLimit(
            @Param("animeView") AnimeViewEntity animeViewEntity,
            @Param("pageable") Pageable pageable,
            @Param("keyword")  String keyword);

    /**
     * 统计总行数
     *
     * @param animeViewEntity 查询条件
     * @return 总行数
     */
    long count( @Param("animeView") AnimeViewEntity animeViewEntity,
                @Param("keyword")  String keyword);

    /**
     * 新增数据
     *
     * @param animeViewEntity 实例对象
     * @return 影响行数
     */
    int insert(AnimeViewEntity animeViewEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AnimeView> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AnimeViewEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AnimeView> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AnimeViewEntity> entities);

    /**
     * 修改数据
     *
     * @param animeViewEntity 实例对象
     * @return 影响行数
     */
    int update(AnimeViewEntity animeViewEntity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 判断某条数据是否存在
     *
     * @param uuid 唯一识别码
     * @return 是否成功
     */
    int isEmptyByUuid(@Param("uuid") String uuid);

}

