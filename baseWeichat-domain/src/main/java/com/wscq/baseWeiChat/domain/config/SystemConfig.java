package com.wscq.baseWeiChat.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 原来使用的是@Component和采用注入的方式调用, 但是个人觉得可以直接放内存里面
 *
 * Created by zs on 16/9/27.
 */
@Component
public class SystemConfig {

    @Value("${wechat.appId}")
    public String appId = "wx53460d4471a3993d";

    @Value("${wechat.secret}")
    public String secret = "2223ad58711183b05e3f2adb63fafbac";

    @Value("${wechat.callback.domain}")
    public String callbackDomain;

    @Value("${wechat.shopping.default.password}")
    public String defaultPassword;

    @Value("${wechat.token}")
    public String token ="zhangshuai";
}
