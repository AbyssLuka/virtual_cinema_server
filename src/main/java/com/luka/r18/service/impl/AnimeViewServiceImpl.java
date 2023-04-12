package com.luka.r18.service.impl;

import com.luka.r18.entity.AnimeViewEntity;
import com.luka.r18.entity.response_object.AnimeView;
import com.luka.r18.mappers.AnimeViewMapper;
import com.luka.r18.service.AnimeViewService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (AnimeView)表服务实现类
 *
 * @author makejava
 * @since 2022-07-15 03:26:49
 */
@Service("animeViewService")
public class AnimeViewServiceImpl implements AnimeViewService {
    @Resource
    private AnimeViewMapper animeViewMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AnimeViewEntity queryById(Integer id) {
        return this.animeViewMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param animeViewEntity 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    @Override
    public Page<AnimeView> queryByPage(AnimeViewEntity animeViewEntity, PageRequest pageRequest, String keyword) {
        long total = this.animeViewMapper.count(animeViewEntity, keyword);
        return new PageImpl<>(this.animeViewMapper.queryAllByLimit(animeViewEntity, pageRequest, keyword), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param animeViewEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeViewEntity insert(AnimeViewEntity animeViewEntity) {
        this.animeViewMapper.insert(animeViewEntity);
        return animeViewEntity;
    }

    /**
     * 修改数据
     *
     * @param animeViewEntity 实例对象
     * @return 实例对象
     */
    @Override
    public AnimeViewEntity update(AnimeViewEntity animeViewEntity) {
        this.animeViewMapper.update(animeViewEntity);
        return this.queryById(animeViewEntity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.animeViewMapper.deleteById(id) > 0;
    }

    /**
     * 判断某条数据是否存在
     *
     * @param uuid 唯一识别码
     * @return 是否成功
     */
    @Override
    public boolean isEmptyByUuid(String uuid) {
        return this.animeViewMapper.isEmptyByUuid(uuid) > 0;
    }
}
