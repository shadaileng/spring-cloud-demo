package com.qpf.spring.cloud.demo.web.admin.ribbon.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qpf.spring.cloud.demo.web.admin.ribbon.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hasError")
    public String demo(String msg) {
        return restTemplate.getForObject(String.format("http://SPRING-CLOUD-DEMO-SERVICE-ADMIN/demo?msg=%s", msg), String.class);
    }

    public String hasError(String msg) {
        return String.format("msg: %s, but request bad", msg);
    }
}
