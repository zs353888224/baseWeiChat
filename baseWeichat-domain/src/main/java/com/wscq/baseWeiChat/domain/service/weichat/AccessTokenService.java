package com.wscq.baseWeiChat.domain.service.weichat;

import java.net.MalformedURLException;

/**
 * 获得微信相关凭证
 * <p>
 * Created by zs on 16/9/27.
 */
public interface AccessTokenService {

    /**
     * 获取access_token
     *
     * @throws MalformedURLException
     */
    public String getAccessToken();

    /**
     * 获取网页调用需要的js凭证
     *
     * @return
     */
    public String getJsTicket();
}
