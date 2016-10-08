package com.wscq.baseWeiChat.domain.service.weichat;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wscq.baseWeiChat.domain.model.WechatEventMessage;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zs on 16/9/30.
 */
@Service
public class WechatMessageParser {
    private static Logger LOGGER = LoggerFactory.getLogger(WechatMessageParser.class);
    @Value("${wechat.token}")
    private String token = "zhangshuai";
    @Value("${wechat.encodingaeskey}")
    private String encodingAesKey = "cuD0qRVacpWLJgozKRqKFxkcJomzlLmv9OEOvWyPs24";
    @Value("${wechat.appId}")
    private String appId = "wx53460d4471a3993d";

    public WechatEventMessage parse(String msgSignature, String timeStamp, String nonce, InputStream inputStream) throws IOException {
        XStream xs = new XStream(new DomDriver());
        xs.alias("xml", WechatEventMessage.class);
        String xmlMsg = IOUtils.toString(inputStream, "UTF-8");
        LOGGER.info("event xml raw msg: {}", xmlMsg);
        String decrypted = decryptMsg(msgSignature, timeStamp, nonce, xmlMsg);
        LOGGER.info("event xml decrypted msg: {}", decrypted);
        return (WechatEventMessage) xs.fromXML(decrypted);
    }

    /**
     * 解密微信发过来的密文
     *
     * @return 加密后的内容
     */
    public String decryptMsg(String msgSignature, String timeStamp, String nonce, String encrypt_msg) {
        /*WXBizMsgCrypt pc;
        String result = "";
        try {
            pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            result = pc.DecryptMsg(msgSignature, timeStamp, nonce, encrypt_msg);
        } catch (AesException e) {
            LOGGER.error("", e);
        }
        return result;*/
        //AES is not fully supported in production server
        return encrypt_msg;
    }

    /**
     * 加密给微信的消息内容
     *
     * @param replayMsg
     * @param timeStamp
     * @param nonce
     * @return
     */
    public String ecryptMsg(String replayMsg, String timeStamp, String nonce) {
       /* WXBizMsgCrypt pc;
        String result = "";
        try {
            pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
            result = pc.EncryptMsg(replayMsg, timeStamp, nonce);
        } catch (AesException e) {
            LOGGER.error("", e);
        }
        return result;*/
        return replayMsg;
    }
}
