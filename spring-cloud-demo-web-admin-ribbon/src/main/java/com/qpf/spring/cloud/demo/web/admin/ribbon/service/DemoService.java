package com.qpf.spring.cloud.demo.web.admin.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public interface DemoService {
    String demo(String msg);
}
