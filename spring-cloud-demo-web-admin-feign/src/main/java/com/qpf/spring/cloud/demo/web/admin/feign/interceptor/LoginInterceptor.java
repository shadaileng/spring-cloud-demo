package com.qpf.spring.cloud.demo.web.admin.feign.interceptor;

import com.qpf.spring.cloud.commons.constant.HttpConstant;
import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.utils.CookieUtils;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
import com.qpf.spring.cloud.demo.web.admin.feign.service.RedisCacheService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Value("${host.sso}")
    private String HOST_SSO;
    @Autowired
    private RedisCacheService redisCacheService;

    private HttpSession session;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = CookieUtils.getCookieValue(request, HttpConstant.COOKIE_TOKEN);
        if (StringUtils.isBlank(token)) {

            try {
                response.sendRedirect(String.format("%s/login?url=%s", HOST_SSO, request.getRequestURL()));
            } catch (IOException e) {
                logger.error(String.format("Error: %s", e.getMessage()));
            }
            return false;
        }
        session = request.getSession();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String token = CookieUtils.getCookieValue(request, HttpConstant.COOKIE_TOKEN);
        if (StringUtils.isNotBlank(token)) {
//            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(HttpConstant.SESSION_USER);
            if (user == null) {
                String resultJson = redisCacheService.get(token);
                if (StringUtils.isNotBlank(resultJson)) {
                    BaseResult result = JsonUtils.json2pojo(resultJson, BaseResult.class);
                    if (result.getResult()) {
                        user = JsonUtils.json2pojo((String) result.getData(), User.class);
                        session.setAttribute(HttpConstant.SESSION_USER, user);
                    } else {
                        // redis中没有token对应的对象
                        CookieUtils.deleteCookie(request, response, HttpConstant.COOKIE_TOKEN);
                    }
                } else {
                    // redis获取对象失败
                    // 网络故障,请稍后重试
                    logger.warn("网络故障,请稍后重试");
                    modelAndView.addObject("msg", "网络故障,请稍后重试");
                    response.sendRedirect(String.format("%s/login?url=%s", HOST_SSO, request.getRequestURL()));
                }
            } else {
                session.setAttribute(HttpConstant.SESSION_USER, user);
            }
        } else {
            response.sendRedirect(String.format("%s/login?url=%s", HOST_SSO, request.getRequestURL()));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
