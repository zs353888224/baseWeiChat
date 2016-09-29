package com.wscq.baseWeiChat.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信接口服务
 *
 * Created by zs on 16/9/26.
 */
@Controller
public class WeiChatController {

    // 微信公众号消息处理
    @RequestMapping("/weichat")
    public void weichat(HttpServletRequest request){
        // 取出关键参数进行判断


    }
}
