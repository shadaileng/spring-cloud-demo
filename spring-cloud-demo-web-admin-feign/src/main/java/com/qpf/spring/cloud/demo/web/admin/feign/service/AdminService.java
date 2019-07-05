package com.qpf.spring.cloud.demo.web.admin.feign.service;

import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.demo.web.admin.feign.service.hystrix.AdminServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(value = "SPRING-CLOUD-DEMO-SERVICE-ADMIN", fallback = AdminServiceHystrix.class)
public interface AdminService {

    @GetMapping("/v1/api/user/page")
    BaseResult page(
            @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
            @RequestParam(value = "length", required = false, defaultValue = "2") Integer length,
            @RequestParam(value = "userJson", required = false) String userJson);
    @GetMapping("/v1/api/user/{id}")
    BaseResult getUserById(@PathVariable(value = "id") Integer id);
    @PostMapping("/v1/api/user/save")
    BaseResult save(@RequestParam("userJson") String userJson);
    @DeleteMapping("/v1/api/user/delete")
    BaseResult delete(@RequestParam("ids") Integer[] ids);
}
