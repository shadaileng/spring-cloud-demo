package com.qpf.spring.cloud.commons.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.domain.BaseDomain;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.service.AbstractService;
import tk.mybatis.mapper.BaseMapper;

import java.util.Arrays;
import java.util.List;

public class AbstractServiceImpl<T extends BaseDomain, M extends BaseMapper<T>> implements AbstractService<T> {

    private M mapper;

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public PageInfo page(int start, int length, T entity) {
        PageHelper.startPage(start, length);
        return new PageInfo<>(mapper.select(entity));
    }

    @Override
    public BaseResult save(T entity) {
        return null;
    }

    @Override
    public BaseResult delete(Integer... ids) {
        int delete = mapper.deleteByIds(ids);
        return delete > 0 ? BaseResult.OK(Arrays.asList(ids), "删除成功") : BaseResult.ER("删除失败");
    }

    @Override
    public BaseResult getById(T entity) {
        entity = mapper.selectByPrimaryKey(entity);
        return entity != null ? BaseResult.OK(entity, "查询成功") : BaseResult.ER("查询失败");
    }
}
