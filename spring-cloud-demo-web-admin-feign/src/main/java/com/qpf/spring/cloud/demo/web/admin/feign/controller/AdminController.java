package com.qpf.spring.cloud.demo.web.admin.feign.controller;

import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.demo.web.admin.feign.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("login")
    public String login(Map<String, Object> map) {
        BaseResult result = adminService.login("0001", "123456");
        map.put("result", result);
        System.out.println(result);
        return "login";
    }
}
