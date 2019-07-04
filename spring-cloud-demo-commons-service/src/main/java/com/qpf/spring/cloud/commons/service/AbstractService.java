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

    /**
     * 保存
     * @param entity
     * @return
     */
    BaseResult save(T entity);

    /**
     * 根据Id删除
     * @param ids
     * @return
     */
    BaseResult delete(Integer... ids);

    /**
     * 根据Id获取单个实体
     * @param entity
     * @return
     */
    BaseResult getById(T entity);
    /**
     * 根据实体获取多个实体
     * @param entity
     * @return
     */
    BaseResult getByBean(T entity);

    /**
     * 根据Id获取多个实体
     * @param ids
     * @return
     */
    BaseResult getByIds(Integer... ids);
}
