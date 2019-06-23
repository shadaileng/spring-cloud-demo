package com.qpf.spring.cloud.demo.web.admin.feign.test.service;

import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.demo.web.admin.feign.WebAdminFeignApplication;
import com.qpf.spring.cloud.demo.web.admin.feign.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebAdminFeignApplication.class})
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Test
    public void testPage() {
        BaseResult page = adminService.page(1, 5, null);
        System.out.println(page);
    }
}
