package com.qpf.spring.cloud.sso.controller;

import com.qpf.spring.cloud.commons.constant.HttpConstant;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.utils.CookieUtils;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
import com.qpf.spring.cloud.sso.service.RedisCacheService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class DispatcherController {

    private final static Logger logger = LoggerFactory.getLogger(DispatcherController.class);

    private RedisCacheService redisCacheService;

    @Autowired
    public DispatcherController(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    @GetMapping({"", "login"})
    public String login(
            @RequestParam(value = "url", required = false) String url,
            HttpServletRequest request,
            HttpServletResponse response,
            Map<String, Object> map) throws Exception {
        String token = CookieUtils.getCookieValue(request, HttpConstant.COOKIE_TOKEN);
        if (StringUtils.isNotBlank(token)) {
            String resultJson = redisCacheService.get(token);
            BaseResult baseResult = JsonUtils.json2pojo(resultJson, BaseResult.class);
            if (baseResult != null && baseResult.getResult()){
                if (baseResult.getData() != null) {
                    if (StringUtils.isNotBlank(url)) {
                        return String.format("redirect:%s", url);
                    }
                    User login = JsonUtils.json2pojo((String) baseResult.getData(), User.class);
                    map.put("loginCode", login.getLoginCode());
                    map.put("username", login.getUserName());
                }
            } else {
                // 网络故障,请稍后重试
                logger.warn("网络故障,请稍后重试");
                map.put("msg", "网络故障,请稍后重试");
            }
        }
        if (StringUtils.isNotBlank(url)) {
            map.put("url", url);
        }
        return "login";
    }
}
