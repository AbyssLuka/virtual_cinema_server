package com.luka.r18.service.impl;

import com.luka.r18.entity.AnimeInfoLinkEntity;
import com.luka.r18.mappers.AnimeInfoLinkMapper;
import com.luka.r18.service.AnimeInfoLinkService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (AnimeInfoLink)表服务实现类
 *
 * @author makejava
 * @since 2022-07-24 09:55:54
 */
@Service("animeInfoLinkService")
public class AnimeInfoLinkServiceImpl implements AnimeInfoLinkService {
    @Resource
    private AnimeInfoLinkMapper animeInfoLinkMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AnimeInfoLinkEntity queryById(Integer id) {
        return this.animeInfoLinkMapper.queryById(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param videoUuid anime_file表主键
     * @return 文件路径
     */
    @Override
    public String querySubtitlePath(String videoUuid) {
        return animeInfoLinkMapper.querySubtitlePath(videoUuid);
    }

    /**
     * 分页查询
     *
     * @param animeInfoLinkEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AnimeInfoLinkEntity> queryByPage(AnimeInfoLinkEntity animeInfoLinkEntity, PageRequest pageRequest) {
        long total = this.animeInfoLinkMapper.count(animeInfoLinkEntity);
        return new PageImpl<>(this.animeInfoLinkMapper.queryAllByLimit(animeInfoLinkEntity, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param animeInfoLinkEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeInfoLinkEntity insert(AnimeInfoLinkEntity animeInfoLinkEntity) {
        this.animeInfoLinkMapper.insert(animeInfoLinkEntity);
        return animeInfoLinkEntity;
    }

    /**
     * 修改数据
     *
     * @param animeInfoLinkEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeInfoLinkEntity update(AnimeInfoLinkEntity animeInfoLinkEntity) {
        this.animeInfoLinkMapper.update(animeInfoLinkEntity);
        return this.queryById(animeInfoLinkEntity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.animeInfoLinkMapper.deleteById(id) > 0;
    }
}
