package com.luka.r18.mappers;

import com.luka.r18.entity.ModelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Model)表数据库访问层
 *
 * @author Luka
 * @since 2024-02-05 04:26:19
 */
@Mapper
public interface ModelMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    List<ModelEntity> queryByUserName(String username);

    /**
     * 通过ID查询单条数据
     *
     * @param uuid 主键
     * @return 实例对象
     */
    ModelEntity queryById(String uuid);

    /**
     * 查询指定行数据
     *
     * @param modelEntity 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ModelEntity> queryAllByLimit(ModelEntity modelEntity, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param modelEntity 查询条件
     * @return 总行数
     */
    long count(ModelEntity modelEntity);

    /**
     * 新增数据
     *
     * @param modelEntity 实例对象
     * @return 影响行数
     */
    int insert(ModelEntity modelEntity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Model> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ModelEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Model> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ModelEntity> entities);

    /**
     * 修改数据
     *
     * @param modelEntity 实例对象
     * @return 影响行数
     */
    int update(ModelEntity modelEntity);

    /**
     * 通过主键删除数据
     *
     * @param uuid 主键
     * @return 影响行数
     */
    int deleteById(String uuid);

}

