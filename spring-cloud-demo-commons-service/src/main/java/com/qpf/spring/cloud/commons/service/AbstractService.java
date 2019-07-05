package com.qpf.spring.cloud.commons.service;

import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.domain.BaseDomain;
import com.qpf.spring.cloud.commons.dto.BaseResult;

import java.util.List;

public interface AbstractService<T extends BaseDomain> {
    /**
     * 查找全部
     * @return 返回结果
     */
    List<T> selectAll();

    /**
     * 分页查询
     * @param start     分页开始
     * @param length    分页大小
     * @param entity    实体类(条件)
     * @return          返回结果
     */
    PageInfo page(int start, int length, T entity);

    /**
     * 保存
     * @param entity    实体类
     * @param fields    检查的字段
     * @return          返回结果
     */
    BaseResult save(T entity, String... fields);

    /**
     * 根据Id删除
     * @param ids       id数组
     * @return          返回结果
     */
    BaseResult delete(Integer... ids);

    /**
     * 根据Id获取单个实体
     * @param entity    实体类
     * @return          返回结果
     */
    BaseResult getById(T entity);
    /**
     * 根据实体获取多个实体
     * @param entity    实体类
     * @return          返回结果
     */
    BaseResult getByBean(T entity);

    /**
     * 根据Id获取多个实体
     * @param ids       id数组
     * @return          返回结果
     */
    BaseResult getByIds(Integer... ids);

    /**
     * 保存时检查字段
     * @param entity    实体类
     * @param fields    字段列表
     * @return          返回结果
     */
    BaseResult checkUser(T entity, List<String> fields);
}
