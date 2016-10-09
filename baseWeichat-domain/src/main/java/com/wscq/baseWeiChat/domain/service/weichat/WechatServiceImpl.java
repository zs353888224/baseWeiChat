package com.wscq.baseWeiChat.domain.service.weichat;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import com.wscq.baseWeiChat.domain.model.WechatEventMessage;
import com.wscq.baseWeiChat.domain.model.WechatUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 微信服务Service
 * <p>
 * Created by zs on 16/9/30.
 */
@Service
public class WechatServiceImpl implements WechatService{

    public static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?lang=zh_CN";

    @Inject
    WechatMessageParser wechatMessageParser;

    @Inject
    AccessTokenService accessTokenService;

    public Object handleWechatPushMessage(HttpServletRequest request, HttpServletResponse response) {
        String encrypt_type = request.getParameter("encrypt_type");
        // 微信加密签名
        String signature = request.getParameter("msg_signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        logger.debug("signature {}, timestamp {}, timestamp {}", signature, timestamp, nonce);
        // 获取对象
        WechatEventMessage wechatEventMessage = null;
        try {
            wechatEventMessage = wechatMessageParser.parse(signature, timestamp, nonce, request.getInputStream());
        } catch (IOException e) {
            logger.error("handle wechat push message error!: {}", e);
        }

        assert wechatEventMessage != null;
        // TODO 日志信息测试时用
        logger.error("this is follow event. the userName is {}. >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", wechatEventMessage.getFromUserName());
        logger.error("event msg detail\nformUserName:{}\ncontent:{}\nencrypt:{}\nlatitude:{}" +
                        "\nlongitude:{}\nmenuId:{}\nmsgId:{}" +
                        "\ntoUserName:{}\nticket:{}\nprecision:{}\neventKey:{}\nevent:{}" +
                        "\nmsgType:{}", wechatEventMessage.getFromUserName(),
                wechatEventMessage.getContent(), wechatEventMessage.getEncrypt(), wechatEventMessage.getLatitude(),
                wechatEventMessage.getLongitude(), wechatEventMessage.getMenuId(), wechatEventMessage.getMsgId(),
                wechatEventMessage.getToUserName(), wechatEventMessage.getTicket(),
                wechatEventMessage.getPrecision(), wechatEventMessage.getEventKey(), wechatEventMessage.getEvent(),
                wechatEventMessage.getMsgType());
        handleMsg(wechatEventMessage);
        return null;
    }

    /**
     * 获取用户的基本信息
     */
    public WechatUserInfo getUserInfo(String openId) {
        String url = URL_GET_USER_INFO + "&openid=" + openId;
        Map info = doGet(url, true);
        WechatUserInfo userInfo = new WechatUserInfo();
        userInfo.setCity(getMapValue(info, "city", String.class, ""));
        userInfo.setCountry(getMapValue(info, "country", String.class, ""));
        userInfo.setGroupid(getMapValue(info, "groupid", Integer.class, 0));
        userInfo.setHeadimgurl(getMapValue(info, "headimgurl", String.class, ""));
        userInfo.setLanguage(getMapValue(info, "language", String.class, ""));
        userInfo.setNickname(getMapValue(info, "nickname", String.class, ""));
        userInfo.setOpenid(getMapValue(info, "openid", String.class, ""));
        userInfo.setProvince(getMapValue(info, "province", String.class, ""));
        userInfo.setRemark(getMapValue(info, "remark", String.class, ""));
        userInfo.setSex(getMapValue(info, "sex", Integer.class, 0));
        userInfo.setSubscribe(getMapValue(info, "subscribe", Integer.class, 0));
        userInfo.setUnionid(getMapValue(info, "unionid", String.class, ""));
        return userInfo;
    }

    /**
     * 按照msgType处理具体业务
     *
     * @param wechatEventMessage
     * @return
     */
    private Object handleMsg(WechatEventMessage wechatEventMessage) {
        String msgType = wechatEventMessage.getMsgType();
        switch (msgType) {
            case "text":
                // TODO 应该是设置自动回复的地方
                logger.debug("handleMsg: this is wechat msg text");
                break;
            case "event":
                // 主要事件处理
                logger.debug("handleMsg: this is wechat msg event");
                handleEvent(wechatEventMessage);
                break;
            case "image":
                logger.debug("handleMsg: this is wechat msg image");
                break;
            case "voice":
                logger.debug("handleMsg: this is wechat msg voice");
                break;
            case "video":
                logger.debug("handleMsg: this is wechat msg video");
                break;
            case "shortvideo":
                logger.debug("handleMsg: this is wechat msg shortvideo");
                break;
            case "location":
                logger.debug("handleMsg: this is wechat msg location");
                break;
            case "link":
                logger.debug("handleMsg: this is wechat msg link");
                break;
            default:
                logger.error("handleMsg: unknow msgType");
        }
        return null;
    }

    /**
     * 处理msgType为event的方法
     *
     * @param wechatEventMessage
     */
    private void handleEvent(WechatEventMessage wechatEventMessage) {
        String event = wechatEventMessage.getEvent();
        switch (event) {
            case "subscribe":
                handleFollowEvent(wechatEventMessage, true);
                break;
            case "unsubscribe":
                handleFollowEvent(wechatEventMessage, false);
                break;
            case "SCAN":// 用户已关注的事件推送
                break;
            case "LOCATION":
                break;
            // 用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报
            case "CLICK":// 点击菜单拉取消息时的事件推送
                break;
            case "VIEW":// 点击菜单跳转链接时的事件推送
                break;
            default:
                logger.error("handleEvent: unknow event");
        }
    }

    /**
     * 处理关注/取消关注事件
     */
    private void handleFollowEvent(WechatEventMessage wechatEventMessage, boolean isFollow) {
        // TODO 获取用户基础信息, 进行存储或删除操作
        WechatUserInfo wechatUserInfo = getUserInfo(wechatEventMessage.getFromUserName());
        logger.error("to user info debug>>>>>>>>>>>>>>>>{}", wechatUserInfo.toString());
    }

    /**
     * 向用户发送消息
     */
    public void sendMsg() {

    }

    private Map doGet(String url, boolean withAccessToken) {
        String requestUrl = withAccessToken ? addAccessToken(url) : url;
        String jsonResp = HttpRequest.get(requestUrl).body();
        return JSON.parseObject(jsonResp, Map.class); // 将json字符串转换为map对象
    }

    /**
     * 链接中加上access_token
     *
     * @param url 请求url
     * @return 添加access_token后的url
     */
    private String addAccessToken(String url) {
        StringBuilder requestUrl = new StringBuilder(url);
        if (requestUrl.indexOf("?") != -1) {
            requestUrl.append("&access_token=").append(accessTokenService.getAccessToken());
        } else {
            requestUrl.append("?access_token=").append(accessTokenService.getAccessToken());
        }
        return requestUrl.toString();
    }

    private <T> T getMapValue(Map<String, Object> bean, String key, Class<T> valueType, Object defaultValue) {
        try {
            if (bean.get(key) != null) {
                return valueType.cast(bean.get(key));
            } else {
                return valueType.cast(defaultValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
