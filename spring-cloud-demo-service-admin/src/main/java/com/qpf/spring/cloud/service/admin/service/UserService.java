package com.qpf.spring.cloud.service.admin.service;

import com.qpf.spring.cloud.commons.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 查找全部
     * @return
     */
    List<User> selectAll();

    /**
     * 注册
     * @param user
     * @return
     */
    User register(User user);

    /**
     * 登陆
     * @param loginCode
     * @param password
     * @return
     */
    User login(String loginCode, String password);
}
