package com.qpf.spring.cloud.sso.service;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;

public interface LoginService {

    /**
     * 注册
     * @param user
     * @return
     */
    BaseResult register(User user);

    /**
     * 登陆
     * @param loginCode
     * @param password
     * @return
     */
    User login(String loginCode, String password);
}
