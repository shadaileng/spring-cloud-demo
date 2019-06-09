package com.qpf.spring.cloud.sso.service.hystrix;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
import com.qpf.spring.cloud.sso.service.RedisCacheService;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheServiceHystrix implements RedisCacheService {

    @Override
    public String set(String key, Object value, long timeout) throws Exception {
        return JsonUtils.obj2json(BaseResult.ER("502: Bad Gateway1"));
    }

    @Override
    public String get(String key) throws Exception {
        return JsonUtils.obj2json(BaseResult.ER("502: Bad Gateway"));
    }
}
