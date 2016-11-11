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
 * Created by zs on 16/11/2.
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenService.class);

    private static final String WECHAT_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    private static final String WECHAT_JS_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    @Inject
    CacheService cacheService;

    @Inject
    SystemConfig systemConfig;

    /**
     * 获取access_token
     *
     * @throws MalformedURLException
     */
    public String getAccessToken() {
        DateTime nowTime = new DateTime();
        long now = nowTime.getMillis();
        // 获取之前先判断缓存中的关键字是否过期
        String accessTokenCache = isPassed(SystemConstants.WECHAT_JS_TICKET, now, 1);
        if (accessTokenCache != null) {
            return accessTokenCache;
        }

        // access过期, 发起请求获取新的token(暂时没有多次访问的情况)
        try {
            logger.info("take access_token from wechat {}", nowTime);
            String requestURL = new URIBuilder(WECHAT_ACCESS_TOKEN_URL)
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
        } else {
            logger.error("the request status is wrong, the access_token is null");
        }
        return accessTokenCache;
    }

    public String getJsTicket() {
        DateTime nowTime = new DateTime();
        long now = nowTime.getMillis();
        // 验证ticket是否过期
        String ticket = isPassed(SystemConstants.WECHAT_JS_TICKET, now, 1);
        if (ticket != null) {
            return ticket;
        }
        String accessTokenCache = getAccessToken();
        try {
            logger.info("take js_ticket form wechat {}", nowTime);
            // https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
            String requestURL = new URIBuilder(WECHAT_JS_TICKET_URL)
                    .addParameter("access_token", accessTokenCache)
                    .addParameter("type", "jsapi")
                    .toString();
            String str = HttpRequest.get(requestURL).body();
            Map map = JSON.parseObject(str, Map.class);
            ticket = (String) map.get("ticket");
            logger.info("js_ticket is: {}", ticket);
        } catch (Exception e) {
            logger.error("get js_ticket error");
            throw new RuntimeException(e);
        }
        if (ticket != null) {
            cacheService.save(SystemConstants.WECHAT_JS_TICKET, ticket + " " + now, 3600);
        }
        return ticket;
    }

    /**
     * 验证缓存中的固定格式的凭证是否过期, 没有过期则返回凭证
     *
     * @param key
     * @param loseHours
     * @return
     */
    private String isPassed(String key, long nowTime, int loseHours) {
        // 获取之前先判断缓存中的关键字是否过期
        String value = (String) cacheService.get(key);
        if (value != null) {
            String[] temp = value.split(" ");
            long expire = Long.parseLong(temp[1]);
            DateTime dateTime = new DateTime(expire);
            // 根据时间戳查看凭证是否过期
            if (dateTime.plusHours(loseHours).getMillis() > nowTime) {
                return temp[0];
            }
        }
        return null;
    }
}
