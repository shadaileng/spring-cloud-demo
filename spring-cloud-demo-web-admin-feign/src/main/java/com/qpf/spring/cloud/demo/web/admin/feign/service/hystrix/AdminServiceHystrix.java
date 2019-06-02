package com.qpf.spring.cloud.demo.web.admin.feign.service.hystrix;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.demo.web.admin.feign.service.AdminService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceHystrix implements AdminService {
    @Override
    public BaseResult register(User user) {
        return BaseResult.ER("502: Bad gateway");
    }

    @Override
    public BaseResult login(String loginCode, String password) {
        return BaseResult.ER("502: Bad gateway");
    }
}
