package com.wscq.baseWeiChat.domain.service.weichat;

import com.wscq.baseWeiChat.domain.model.WechatEventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zs on 16/9/30.
 */
@Service
public class WechatService {

    public static final Logger logger = LoggerFactory.getLogger(WechatService.class);

    @Inject
    WechatMessageParser wechatMessageParser;

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
        // TODO 按照事件类型不同进行处理, 先在这里处理关注已及取消关注事件
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


    private Object handleMsg(WechatEventMessage wechatEventMessage) {
        String msgType = wechatEventMessage.getMsgType();
        switch (msgType) {
            case "text":
                // TODO 设置自动回复的地方
                logger.debug("this is wevhat msg text");
                break;
            case "event":
                // 主要事件处理
                logger.debug("this is wevhat msg event");
                handleEvent(wechatEventMessage);
                break;
            case "image":
                logger.debug("this is wevhat msg image");
                break;
            case "voice":
                logger.debug("this is wevhat msg voice");
                break;
            case "video":
                logger.debug("this is wevhat msg video");
                break;
            case "shortvideo":
                logger.debug("this is wevhat msg shortvideo");
                break;
            case "location":
                logger.debug("this is wevhat msg location");
                break;
            case "link":
                logger.debug("this is wevhat msg link");
                break;
        }
        return null;
    }

    /**
     * 微信event事件处理
     *
     * @param wechatEventMessage
     */
    private void handleEvent(WechatEventMessage wechatEventMessage){
        String event = wechatEventMessage.getEvent();

    }

    /**
     * 处理关注取消关注事件
     */
    private void handleFollowEvent() {

    }

    /**
     * 获取用户的基本信息
     */
    public void getUserInfo() {

    }

    /**
     * 想用户发送消息
     */
    public void sendMsg() {

    }
}
