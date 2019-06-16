package com.qpf.demo.test;


import com.qpf.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

//@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class DemoTest {
    @Value("${IP_ADDR}")
    private String java_home;

    @Test
    public void testDemo() {
        System.out.println(java_home);
    }
}
