package com.qpf.spring.cloud.demo.zuul.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebAdminRibbonFallbackProvider implements FallbackProvider {
    @Autowired
    private CommonResponse commonResponse;

    @Override
    public String getRoute() {
        return "spring-cloud-demo-web-admin-ribbon";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return commonResponse;
    }
}
