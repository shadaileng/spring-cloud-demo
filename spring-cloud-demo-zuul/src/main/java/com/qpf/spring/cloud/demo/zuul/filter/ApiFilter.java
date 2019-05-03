package com.qpf.spring.cloud.demo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiFilter extends ZuulFilter {
    private final static Logger logger = LoggerFactory.getLogger(ApiFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        logger.info("{}>>>{}", request.getMethod(), request.getRequestURI());

        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            logger.warn("Token is Empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            HttpServletResponse response = context.getResponse();
            try {
                response.setHeader("contentType", "text/html;charset=utf-8");
                response.getWriter().println("Token is Empty");
            } catch (IOException e) {
                logger.error("response error: {}", e.getMessage());
            }
        } else {
            logger.info("status: Ok");
        }

        return null;
    }
}
