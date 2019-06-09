package com.qpf.spring.cloud.sso.controller;

import com.qpf.spring.cloud.commons.constant.HttpConstant;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.utils.CookieUtils;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
import com.qpf.spring.cloud.sso.service.DemoService;
import com.qpf.spring.cloud.sso.service.LoginService;
import com.qpf.spring.cloud.sso.service.RedisCacheService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api")
public class LoginController {

    private LoginService loginService;
    private RedisCacheService redisCacheService;

    @Autowired
    public LoginController(LoginService loginService, RedisCacheService redisCacheService) {
        this.loginService = loginService;
        this.redisCacheService = redisCacheService;
    }

    @PostMapping("login")
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginCode", value = "登录帐号", required = true, dataTypeClass = String.class, paramType = "form"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, dataTypeClass = String.class, paramType = "form"),
    })
    public BaseResult login(String loginCode, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BaseResult result = checkLogin(loginCode, password);
        if (result != null) {
            return result;
        }
        User login = loginService.login(loginCode, password);
        if (login != null) {
            String token = UUID.randomUUID().toString();
            String redisResult = redisCacheService.set(token, JsonUtils.obj2json(login), 60 * 60 * 24 * 7);
            if (redisResult != null ){
                result = JsonUtils.json2pojo(redisResult, BaseResult.class);
                if (result.getResult()) {
                    CookieUtils.setCookie(request, response, HttpConstant.COOKIE_TOKEN, token);
                    request.getSession().setAttribute(HttpConstant.SESSION_USER, login);
                    result = BaseResult.OK(login, "登陆成功");
                }
            } else {
                result = BaseResult.ER("登陆失败: 网络故障");
            }
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


    @GetMapping("logout")
    public BaseResult logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BaseResult result = null;

        String token = CookieUtils.getCookieValue(request, HttpConstant.COOKIE_TOKEN);

        if (StringUtils.isNotBlank(token)) {
            String redisResult = redisCacheService.set(token, "-", 1);
            if (redisResult != null ){
                result = JsonUtils.json2pojo(redisResult, BaseResult.class);
                if (result.getResult()) {
                    CookieUtils.deleteCookie(request, response, HttpConstant.COOKIE_TOKEN);
                    request.getSession().invalidate();
                    result.setMessage("登出成功");
                }
            } else {
                result = BaseResult.ER("登出失败: 网络故障");
            }
        } else {
            result = BaseResult.ER("登出失败: 用户未登录");
        }

        return result;
    }
}
