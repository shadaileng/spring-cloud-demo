package com.qpf.spring.cloud.sso.service.hystrix;

import com.qpf.spring.cloud.commons.domain.User;
import com.qpf.spring.cloud.commons.dto.BaseResult;
import com.qpf.spring.cloud.commons.utils.JsonUtils;
import com.qpf.spring.cloud.sso.service.LoginService;
import com.qpf.spring.cloud.sso.service.RedisCacheService;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceHystrix implements LoginService {


    @Override
    public String register(User user) throws Exception {
        return JsonUtils.obj2json(BaseResult.ER("502: Bad Gateway1"));
    }

    @Override
    public String login(String loginCode, String password) throws Exception {
        return JsonUtils.obj2json(BaseResult.ER("502: Bad Gateway1"));
    }
}
