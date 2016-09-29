package com.wscq.baseWeiChat.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zs on 16/9/27.
 */
@Component
public class SystemConfig {

    @Value("${wechat.appId}")
    public String appId;

    @Value("${wechat.secret}")
    public String secret;

    @Value("${wechat.callback.domain}")
    public String callbackDomain;

    @Value("${wechat.shopping.default.password}")
    public String defaultPassword;

    @Value("${wechat.token}")
    public String token;
}
