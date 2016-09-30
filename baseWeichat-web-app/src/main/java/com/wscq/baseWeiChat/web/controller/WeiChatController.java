package com.wscq.baseWeiChat.web.controller;

import com.wscq.baseWeiChat.domain.config.SystemConfig;
import com.wscq.baseWeiChat.domain.util.WechatAuthenticationUtil;
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
        // 判断是否是微信的主动请求
        if (echostr != null) {
            if (checkSignature(request)){
                response.getWriter().write(echostr);
                logger.info("echo: {} from weichat service.", echostr);
            }
        } else {// 处理微信的消息队列
            // TODO 以后再补上
        }
    }

    /**
     * 验证微信的主动认证
     *
     * @param request
     * @return
     */
    private boolean checkSignature(HttpServletRequest request){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String[] strs = {systemConfig.token, timestamp, nonce};
        Arrays.sort(strs);// 字典序排序
        String encodeStr = WechatAuthenticationUtil.encode(strs[0] + strs[1] + strs[2]);
        return encodeStr.equals(signature);
    }
}
