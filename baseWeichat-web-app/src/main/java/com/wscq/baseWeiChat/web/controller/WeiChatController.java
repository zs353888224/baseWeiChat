package com.wscq.baseWeiChat.web.controller;

import com.wscq.baseWeiChat.domain.config.SystemConfig;
import com.wscq.baseWeiChat.domain.service.weichat.WechatService;
import com.wscq.baseWeiChat.domain.util.WechatAuthenticationUtil;
import com.wscq.baseWeiChat.web.controller.common.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 微信接口服务
 * <p>
 * Created by zs on 16/9/26.
 */
@Controller
public class WeiChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeiChatController.class);

    @Inject
    SystemConfig systemConfig;

    @Inject
    WechatService wechatService;

    /**
     * 处理微信的统一消息
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/wechat")
    @ResponseBody
    public void weichat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String echostr = request.getParameter("echostr");
        // 判断是否是微信的验证请求
        if (echostr != null) {
            if (checkSignature(request)) {
                response.getWriter().write(echostr);
                logger.info("echo: {} from weichat service.", echostr);
            }
        } else {
            // 处理微信主动发起的消息, 包括:
            // 各种事件推送: 用户关注/取消关注, 带参数二维码, 上报地理位置, 自定义菜单等
            // 用户发送的消息: 文本, 图片, 视频等
            wechatService.handleWechatPushMessage(request, response);
            // TODO 此处为防止微信端重复发起请求设置的默认回复, 当业务扩展的时候因更改此处逻辑
            response.getWriter().write(BaseResult.SUCCESS);
        }
    }

    /**
     * 验证微信的主动认证的签名
     *
     * @param request
     * @return
     */
    private boolean checkSignature(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String[] strs = {systemConfig.token, timestamp, nonce};
        Arrays.sort(strs);// 字典序排序
        String encodeStr = WechatAuthenticationUtil.encode(strs[0] + strs[1] + strs[2]);
        return encodeStr.equals(signature);
    }
}
