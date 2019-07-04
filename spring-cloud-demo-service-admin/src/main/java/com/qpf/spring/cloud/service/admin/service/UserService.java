package com.qpf.spring.cloud.service.admin.service;

import com.github.pagehelper.PageInfo;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.service.AbstractService;

import java.util.List;

public interface UserService extends AbstractService<User> {
    BaseResult login(String loginCode, String password);

    BaseResult register(User user);
}
