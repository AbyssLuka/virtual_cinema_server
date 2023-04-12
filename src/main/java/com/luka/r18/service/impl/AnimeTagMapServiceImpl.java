package com.luka.r18.service.impl;

import com.luka.r18.entity.AnimeTagMapEntity;
import com.luka.r18.mappers.AnimeTagMapMapper;
import com.luka.r18.service.AnimeTagMapService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (AnimeTagMap)表服务实现类
 *
 * @author makejava
 * @since 2022-09-21 00:38:41
 */
@Service("animeTagMapService")
public class AnimeTagMapServiceImpl implements AnimeTagMapService {
    @Resource
    private AnimeTagMapMapper animeTagMapMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param animeViewUuid 主键
     * @return 实例对象
     */
    @Override
    public AnimeTagMapEntity queryAnimeTag(String animeViewUuid) {
        return this.animeTagMapMapper.queryAnimeTag(animeViewUuid);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AnimeTagMapEntity queryById(Integer id) {
        return this.animeTagMapMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param animeTagMapEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AnimeTagMapEntity> queryByPage(AnimeTagMapEntity animeTagMapEntity, PageRequest pageRequest) {
        long total = this.animeTagMapMapper.count(animeTagMapEntity);
        return new PageImpl<>(this.animeTagMapMapper.queryAllByLimit(animeTagMapEntity, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param animeTagMapEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeTagMapEntity insert(AnimeTagMapEntity animeTagMapEntity) {
        this.animeTagMapMapper.insert(animeTagMapEntity);
        return animeTagMapEntity;
    }

    /**
     * 修改数据
     *
     * @param animeTagMapEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeTagMapEntity update(AnimeTagMapEntity animeTagMapEntity) {
        this.animeTagMapMapper.update(animeTagMapEntity);
        return this.queryById(animeTagMapEntity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.animeTagMapMapper.deleteById(id) > 0;
    }
}
