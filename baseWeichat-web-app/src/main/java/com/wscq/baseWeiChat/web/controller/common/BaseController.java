package com.wscq.baseWeiChat.web.controller.common;

import com.wscq.baseWeiChat.domain.model.Account;
import com.wscq.baseWeiChat.domain.service.security.WscqUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础controller类可获得当前用户的相关信息
 * <p/>
 * Created by zs on 16/9/20.
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 检查当前用户是否已登录
     *
     * @param request
     * @return
     */
    protected boolean isLogined(HttpServletRequest request) {
        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        return !(securityContext == null || securityContext.getAuthentication() == null);
    }

    /**
     * 获取当前登录的用户信息
     *
     * @param request
     * @return
     */
    protected Account getUserInfo(HttpServletRequest request) {
        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        WscqUserDetails userDetails = (WscqUserDetails) securityContext.getAuthentication().getPrincipal();
        return userDetails.getAccount();
    }
}
