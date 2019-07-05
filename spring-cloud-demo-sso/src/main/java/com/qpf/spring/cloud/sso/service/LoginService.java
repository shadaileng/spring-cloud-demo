package com.qpf.spring.cloud.sso.service;

import com.qpf.spring.cloud.sso.service.hystrix.LoginServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-ADMIN", fallback = LoginServiceHystrix.class)
public interface LoginService {

    /**
     * 注册
     * @param userJson      注册对象
     * @return              返回结果
     * @throws Exception    抛出异常
     */
    @PostMapping("v1/api/user/register")
    String register(@RequestParam("userJson")String userJson) throws Exception;

    /**
     * 登陆
     * @param loginCode     登陆账号
     * @param password      登陆密码
     * @return              返回结果
     * @throws Exception    抛出异常
     */
    @GetMapping("v1/api/user/login")
    String login(@RequestParam("loginCode") String loginCode, @RequestParam("password") String password) throws Exception;
}
