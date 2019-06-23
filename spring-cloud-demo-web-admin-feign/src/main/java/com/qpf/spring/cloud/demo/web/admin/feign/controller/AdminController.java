package com.qpf.spring.cloud.demo.web.admin.feign.controller;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
import com.qpf.spring.cloud.demo.web.admin.feign.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("page")
    public BaseResult loadPage(
            HttpServletRequest request,
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "2") Integer length,
            @RequestParam(required = false) User user) {

        BaseResult result = null;
        try {
            String userJson = JsonUtils.obj2json(user);
            result = adminService.page(start, length, userJson);
        }catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }

        return result;
    }
}
