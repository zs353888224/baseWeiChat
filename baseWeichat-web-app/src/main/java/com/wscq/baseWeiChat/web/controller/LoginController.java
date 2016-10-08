package com.wscq.baseWeiChat.web.controller;

import com.wscq.baseWeiChat.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 * Created by zs on 16/9/20.
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping("/")
    public String toIndex(){
        return "redirect:/index";
    }

    /**
     * 进入登录页面
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String error = request.getParameter("error");
        if (error != null && "true".equalsIgnoreCase(error)) {
            model.addAttribute("loginError", Boolean.TRUE);
        }
        return "login/login";
    }

    /**
     * 测试主页
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request) {

        String name = this.getUserInfo(request).getUserName();
        model.addAttribute("name", name);
        // 微信用户自动登录
        return "login/index";
    }
}
