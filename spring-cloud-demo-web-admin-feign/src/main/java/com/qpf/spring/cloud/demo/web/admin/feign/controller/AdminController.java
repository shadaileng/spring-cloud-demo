package com.qpf.spring.cloud.demo.web.admin.feign.controller;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
import com.qpf.spring.cloud.demo.web.admin.feign.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    private AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping("page")
    public BaseResult loadPage(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "2") Integer length,
            @RequestParam(required = false) User user) {

        BaseResult result;
        try {
            String userJson = JsonUtils.obj2json(user);
            result = adminService.page(start, length, userJson);
        }catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }

        return result;
    }
    @GetMapping("{id}")
    public BaseResult getUserById(@PathVariable("id") Integer id) {

        BaseResult result;
        try {
            result = adminService.getUserById(id);
        }catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }

        return result;
    }

    @PostMapping("save")
    public BaseResult save(User user) {
        BaseResult result;
        try {
            user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            user.setUserCode("0002");
            user.setLoginCode("0002");
            user.setMobile("13800000000");
            user.setSex("1");
            user.setUserType("1");
            user.setMgrType("1");
            user.setStatus("0");
            user.setCorpCode("B");
            user.setCorpName("Bank1");
            user.setCreateBy("1");
            user.setUpdateBy("1");
            result = adminService.save(JsonUtils.obj2json(user));
        }catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }
        return result;
    }
    @PostMapping("delete")
    public BaseResult delete(Integer[] ids) {

        BaseResult result;
        try {
            result = adminService.delete(ids);
        }catch (Exception e) {
            result = BaseResult.ER(String.format("ERROR: %s", e.getMessage()));
        }

        return result;
    }
}
