package com.qpf.spring.cloud.demo.web.admin.feign.service;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.demo.web.admin.feign.service.hystrix.AdminServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-ADMIN", fallback = AdminServiceHystrix.class)
public interface AdminService {
    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    BaseResult register(@RequestParam(value = "user", required = true) User user);

    /**
     * 登录
     * @param loginCode
     * @param password
     * @return
     */
    @PostMapping("login")
    BaseResult login(
            @RequestParam(value = "loginCode", required = true) String loginCode,
            @RequestParam(value = "password", required = true) String password
    );
}
