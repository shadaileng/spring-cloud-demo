package com.qpf.spring.cloud.service.admin.service;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.service.AbstractService;

public interface UserService extends AbstractService<User> {
    BaseResult login(String loginCode, String password);
}
