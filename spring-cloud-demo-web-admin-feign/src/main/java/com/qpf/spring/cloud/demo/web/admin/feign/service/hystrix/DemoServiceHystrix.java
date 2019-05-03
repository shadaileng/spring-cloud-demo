package com.qpf.spring.cloud.demo.web.admin.feign.service.hystrix;

import com.qpf.spring.cloud.demo.web.admin.feign.service.DemoService;
import org.springframework.stereotype.Component;

@Component
public class DemoServiceHystrix implements DemoService {
    @Override
    public String demo(String msg) {
        return String.format("msg: %s, but request bad", msg);
    }
}
