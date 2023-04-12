package com.luka.r18.service.impl;

import com.luka.r18.entity.AnimeCollectEntity;
import com.luka.r18.entity.response_object.AnimeCollectView;
import com.luka.r18.mappers.AnimeCollectMapper;
import com.luka.r18.service.AnimeCollectService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (AnimeCollect)表服务实现类
 *
 * @author makejava
 * @since 2022-08-30 05:03:58
 */
@Service("animeCollectService")
public class AnimeCollectServiceImpl implements AnimeCollectService {
    @Resource
    private AnimeCollectMapper animeCollectMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AnimeCollectEntity queryById(Integer id) {
        return this.animeCollectMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param animeCollectEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AnimeCollectView> queryByPage(AnimeCollectEntity animeCollectEntity, PageRequest pageRequest) {
        long total = this.animeCollectMapper.count(animeCollectEntity);
        return new PageImpl<>(this.animeCollectMapper.queryAllByLimit(animeCollectEntity, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param animeCollectEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeCollectEntity insert(AnimeCollectEntity animeCollectEntity) {
        this.animeCollectMapper.insert(animeCollectEntity);
        return animeCollectEntity;
    }

    /**
     * 修改数据
     *
     * @param animeCollectEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeCollectEntity update(AnimeCollectEntity animeCollectEntity) {
        this.animeCollectMapper.update(animeCollectEntity);
        return this.queryById(animeCollectEntity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.animeCollectMapper.deleteById(id) > 0;
    }

    @Override
    public AnimeCollectEntity selectByEntity(AnimeCollectEntity animeCollectEntity) {
        return animeCollectMapper.selectByEntity(animeCollectEntity);
    }
}
