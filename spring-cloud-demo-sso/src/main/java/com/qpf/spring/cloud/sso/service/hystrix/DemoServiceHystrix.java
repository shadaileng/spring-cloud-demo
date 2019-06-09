package com.qpf.spring.cloud.sso.service.hystrix;

import com.qpf.spring.cloud.sso.service.DemoService;
import org.springframework.stereotype.Component;

@Component
public class DemoServiceHystrix implements DemoService {
    @Override
    public String demo(String msg) {
        return String.format("msg: %s, but request bad", msg);
    }

    @Override
    public String pathDemo(String msg) {
        return String.format("msg: %s, but request bad", msg);
    }

}
