package com.wscq.baseWeiChat.domain.service.weichat;

import com.wscq.baseWeiChat.domain.model.WechatUserInfo;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zs on 16/10/9.
 */
public interface WechatService {

    /**
     * 处理微信主动发来的信息
     *
     * @param request
     * @param response
     * @return
     */
    Object handleWechatPushMessage(HttpServletRequest request, HttpServletResponse response);

    /**
     * 通过获得的用户id获得用户的基本信息
     *
     * @param openId
     * @return
     */
    WechatUserInfo getUserInfo(String openId);

    /**
     * 获取js验证JSON
     *
     * @return
     */
    JSONObject getJsTicket(String url) throws JSONException;
}
