package com.luka.r18.service.impl;

import com.luka.r18.entity.ComicViewEntity;
import com.luka.r18.entity.response_object.ComicView;
import com.luka.r18.mappers.ComicViewMapper;
import com.luka.r18.service.ComicViewService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (ComicView)表服务实现类
 *
 * @author makejava
 * @since 2022-11-09 16:27:39
 */
@Service("comicViewService")
public class ComicViewServiceImpl implements ComicViewService {
    @Resource
    private ComicViewMapper comicViewMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param uuid 主键
     * @return 实例对象
     */
    @Override
    public ComicViewEntity queryById(String uuid) {
        return this.comicViewMapper.queryById(uuid);
    }

    /**
     * 分页查询
     *
     * @param comicViewEntity 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ComicView> queryByPage(ComicViewEntity comicViewEntity, PageRequest pageRequest) {
        long total = this.comicViewMapper.count(comicViewEntity);
        return new PageImpl<>(this.comicViewMapper.queryAllByLimit(comicViewEntity, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param comicViewEntity 实例对象
     * @return 实例对象
     */
    @Override
    public ComicViewEntity insert(ComicViewEntity comicViewEntity) {
        System.out.println(comicViewEntity.toString());
        this.comicViewMapper.insert(comicViewEntity);
        return comicViewEntity;
    }

    /**
     * 修改数据
     *
     * @param comicViewEntity 实例对象
     * @return 实例对象
     */
    @Override
    public ComicViewEntity update(ComicViewEntity comicViewEntity) {
        this.comicViewMapper.update(comicViewEntity);
        return this.queryById(comicViewEntity.getUuid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uuid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String uuid) {
        return this.comicViewMapper.deleteById(uuid) > 0;
    }
}
