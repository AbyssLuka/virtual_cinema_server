package com.luka.r18.service.impl;

import com.luka.r18.entity.AnimeTagEntity;
import com.luka.r18.mappers.AnimeTagMapper;
import com.luka.r18.service.AnimeTagService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (AnimeTag)表服务实现类
 *
 * @author makejava
 * @since 2022-09-21 00:25:09
 */
@Service("animeTagService")
public class AnimeTagServiceImpl implements AnimeTagService {
    @Resource
    private AnimeTagMapper animeTagMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param uuid 主键
     * @return 实例对象
     */
    @Override
    public AnimeTagEntity queryByUuid(String uuid) {
        return this.animeTagMapper.queryByUuid(uuid);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AnimeTagEntity queryById(Integer id) {
        return this.animeTagMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param animeTagEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AnimeTagEntity> queryByPage(AnimeTagEntity animeTagEntity, PageRequest pageRequest) {
        long total = this.animeTagMapper.count(animeTagEntity);
        return new PageImpl<>(this.animeTagMapper.queryAllByLimit(animeTagEntity, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param animeTagEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeTagEntity insert(AnimeTagEntity animeTagEntity) {
        this.animeTagMapper.insert(animeTagEntity);
        return animeTagEntity;
    }

    /**
     * 修改数据
     *
     * @param animeTagEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeTagEntity update(AnimeTagEntity animeTagEntity) {
        this.animeTagMapper.update(animeTagEntity);
        return this.queryById(animeTagEntity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.animeTagMapper.deleteById(id) > 0;
    }
}
