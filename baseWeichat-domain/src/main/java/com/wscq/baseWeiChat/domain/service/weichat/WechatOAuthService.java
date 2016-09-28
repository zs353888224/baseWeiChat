package com.wscq.baseWeiChat.domain.service.weichat;

import com.wscq.baseWeiChat.domain.config.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by zs on 16/9/27.
 */
@Service
public class WechatOAuthService {

    private static final Logger logger = LoggerFactory.getLogger(WechatOAuthService.class);

    private static final String getTokenUrl = "";

    @Inject
    SystemConfig systemConfig;

    // 将accesstoken放到缓存当中, 目前没有缓存服务, 暂时停止

    //
}
