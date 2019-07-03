package com.qpf.spring.cloud.commons.service;

import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.domain.BaseDomain;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;

import java.util.List;

public interface AbstractService<T extends BaseDomain> {
    /**
     * 查找全部
     * @return
     */
    List<T> selectAll();

    /**
     * 分页查询
     * @param start
     * @param length
     * @param entity
     * @return
     */
    PageInfo page(int start, int length, T entity);

    BaseResult save(T entity);

    BaseResult delete(Integer... ids);

    BaseResult getById(T entity);
}
