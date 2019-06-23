package com.qpf.spring.cloud.service.admin.service;

import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;

import java.util.List;

public interface UserService {
    /**
     * 查找全部
     * @return
     */
    List<User> selectAll();

    /**
     * 分页
     * @param user
     * @return
     */
    PageInfo page(int start, int length, User user);

    BaseResult save(User user);

    BaseResult delete(User user);

    BaseResult getUserById(User user);

}
