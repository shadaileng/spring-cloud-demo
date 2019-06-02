package com.qpf.spring.cloud.service.redis.test.service;

import com.qpf.spring.cloud.service.redis.RedisApplication;
import com.qpf.spring.cloud.service.redis.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
public class RedisServiceTest {
    @Autowired
    private RedisService redisService;
    @Test
    public void testSet() {
        redisService.set("a", 1, 300);
    }
    @Test
    public void testGet() {
        Object val = redisService.get("a");
        System.out.println(val);
        System.out.println(String.valueOf(val));
        Assert.assertEquals(String.valueOf(1), String.valueOf(val));
    }
}
