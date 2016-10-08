package com.wscq.baseWeiChat.domain.service.weichat;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import com.wscq.baseWeiChat.domain.config.SystemConfig;
import com.wscq.baseWeiChat.domain.constants.SystemConstants;
import com.wscq.baseWeiChat.domain.service.cache.CacheService;
import org.apache.http.client.utils.URIBuilder;
import org.elasticsearch.common.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * 服务器调用微信相关的服务
 * <p>
 * Created by zs on 16/9/27.
 */
@Service
public class AccessTokenService {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenService.class);

    @Inject
    CacheService cacheService;

    @Inject
    SystemConfig systemConfig;

    /**
     * 获取access_token
     *
     * @throws MalformedURLException
     */
    public String getAccessToken(){
        DateTime nowTime = new DateTime();
        long now = nowTime.getMillis();
        // 获取之前先判断缓存中的关键字是否过期
        String accessTokenCache = (String) cacheService.get(SystemConstants.WEICHAT_ACCESS_TOKEN);
        if (accessTokenCache != null) {
            String[] temp = accessTokenCache.split(" ");
            long expire = Long.parseLong(temp[1]);
            DateTime dateTime = new DateTime(expire);
            // 根据时间戳查看token是否过期
            if (dateTime.plusHours(1).getMillis() > now) {
                return temp[0];
            }
        }
        // access过期, 发起请求获取新的token
        try{
            String requestURL = new URIBuilder(SystemConstants.WECHAT_ACCESS_TOKEN_URL)
                    .addParameter("appid", systemConfig.appId)
                    .addParameter("secret", systemConfig.secret)
                    .addParameter("grant_type", "client_credential")
                    .toString();
            String str = HttpRequest.get(requestURL).body();
            Map map = JSON.parseObject(str, Map.class);
            accessTokenCache = (String) map.get("access_token");
        } catch (Exception e) {
            logger.error("get access_token error");
            throw new RuntimeException(e);
        }
        if (accessTokenCache != null) {
            cacheService.save(SystemConstants.WEICHAT_ACCESS_TOKEN, accessTokenCache + " " + now, 3600);
        }
        return accessTokenCache;
    }
}
