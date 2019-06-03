package com.qpf.spring.cloud.service.admin.controller;

import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.service.admin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("login")
    public BaseResult login(String loginCode, String password) {
        BaseResult result = checkLogin(loginCode, password);
        if (result != null) {
            return result;
        }
        User login = userService.login(loginCode, password);
        if (login != null) {
            result = BaseResult.OK(login, "登陆成功");
        } else {
            result = BaseResult.ER("登陆失败: 登录帐号或者密码错误");
        }
        return result;
    }

    /**
     * 检查登陆参数
     * @return
     */
    private BaseResult checkLogin(String loginCode, String password) {
        BaseResult result = null;
        List<BaseResult.Error> errors = new ArrayList<>();
        if (StringUtils.isBlank(loginCode)) {
            BaseResult.Error error = new BaseResult.Error();
            error.setField("loginCode");
            error.setMessage("登陆号码不能为空");
            errors.add(error);
        }
        if (StringUtils.isBlank(password)) {
            BaseResult.Error error = new BaseResult.Error();
            error.setField("password");
            error.setMessage("密码不能为空");
            errors.add(error);
        }
        if (errors.size() > 0) {
            result = BaseResult.ER(errors);
        }

        return result;
    }
}
