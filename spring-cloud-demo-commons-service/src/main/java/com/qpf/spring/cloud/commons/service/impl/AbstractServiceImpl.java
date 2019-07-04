package com.qpf.spring.cloud.commons.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.domain.BaseDomain;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.BaseMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public class AbstractServiceImpl<T extends BaseDomain, M extends BaseMapper<T>> implements AbstractService<T> {
    @Autowired
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
    @Transactional
    public BaseResult save(T entity) {
        Integer id = entity.getId();
        int save;
        String now = String.valueOf(new Date().getTime());
        if (id != null) {
            entity.setUpdateDate(now);
            save = mapper.updateByPrimaryKeySelective(entity);
        } else {
            entity.setCreateDate(now);
            entity.setUpdateDate(now);
            save = mapper.insert(entity);
        }
        return save > 0 ? BaseResult.OK(entity, "保存成功") : BaseResult.ER("保存失败");
    }

    @Override
    @Transactional
    public BaseResult delete(Integer... ids) {
        int delete = mapper.deleteByIds(ids);
        return delete > 0 ? BaseResult.OK(Arrays.asList(ids), "删除成功") : BaseResult.ER("删除失败");
    }

    @Override
    public BaseResult getById(T entity) {
        entity = mapper.selectByPrimaryKey(entity);
        return entity != null ? BaseResult.OK(entity, "查询成功") : BaseResult.ER("查询失败");
    }

    @Override
    public BaseResult getByBean(T entity) {
        List<T> select = mapper.select(entity);
        return select != null && select.size() > 0 ? BaseResult.OK(select, String.format("查询成功 | size: %s", select.size())) : BaseResult.ER("查询失败");
    }

    @Override
    public BaseResult getByIds(Integer... ids) {
        List<T> entities = mapper.selectByIds(ids);
        return entities != null && entities.size() > 0 ? BaseResult.OK(entities, String.format("查询成功 | size: %s", entities.size())) : BaseResult.ER("查询失败");
    }
}
