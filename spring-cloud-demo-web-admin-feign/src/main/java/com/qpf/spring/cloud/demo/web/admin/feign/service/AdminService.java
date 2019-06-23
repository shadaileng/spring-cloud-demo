package com.qpf.spring.cloud.demo.web.admin.feign.service;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.demo.web.admin.feign.service.hystrix.AdminServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-ADMIN", fallback = AdminServiceHystrix.class)
public interface AdminService {

    @GetMapping("/v1/api/page")
    public BaseResult page(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "2") Integer length,
            @RequestParam(required = false) String userJson);
}
