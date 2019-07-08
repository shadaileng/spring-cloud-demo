package com.qpf.spring.cloud.service.redis.controller;

import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.service.redis.service.RedisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api")
public class RedisController {

    private final static Logger logger = LoggerFactory.getLogger(RedisController.class);
    private RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @ApiOperation("添加键值元素")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "键", dataTypeClass = String.class, paramType = "path"),
            @ApiImplicitParam(name = "value", value = "值", dataTypeClass = String.class, paramType = "path"),
            @ApiImplicitParam(name = "timeout", value = "键值保留时间, 单位: 秒", dataType = "long", paramType = "path")
    })
    @PostMapping("set/{key}/{value}/{timeout}")
    public BaseResult set(
            @PathVariable(value = "key") String key,
            @PathVariable(value = "value") Object value,
            @PathVariable(value = "timeout") long timeout) {
        BaseResult result;
        try {
            redisService.set(key, value, timeout);
            result = BaseResult.OK();
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
            result = BaseResult.ER(e.getMessage());
        }

        return result;
    }

    @ApiOperation("根据键获取值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "键", dataTypeClass = String.class, paramType = "path"),
    })
    @GetMapping("get/{key}")
    public BaseResult get(@PathVariable(value = "key") String key) {
        BaseResult result;
        try {
            Object val = redisService.get(key);
            result = val == null ? BaseResult.ER("未获取对应值"): BaseResult.OK(val);
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
            result = BaseResult.ER(e.getMessage());
        }
        return result;
    }
}
