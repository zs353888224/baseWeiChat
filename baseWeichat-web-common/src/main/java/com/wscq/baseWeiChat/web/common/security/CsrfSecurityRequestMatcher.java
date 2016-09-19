package com.wscq.baseWeiChat.web.common.security;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by zs on 16/9/19.
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsrfSecurityRequestMatcher.class);

    private Pattern allowedMethods = Pattern
            .compile("^(GET|HEAD|TRACE|OPTIONS)$");

    public boolean matches(HttpServletRequest request) {
        if (CollectionUtils.isNotEmpty(execludeUrls)) {
            String servletPath = request.getServletPath();
            for (String url : execludeUrls) {
                if (servletPath.startsWith(url)) {
                    LOGGER.info("{} excluded from CSRF verification", url);
                    return false;
                }
            }
        }
        return !allowedMethods.matcher(request.getMethod()).matches();
    }

    /**
     * urls to be excluded, configured in spring-security.xml
     */
    private List<String> execludeUrls;

    public List<String> getExecludeUrls() {
        return execludeUrls;
    }

    public void setExecludeUrls(List<String> execludeUrls) {
        this.execludeUrls = execludeUrls;
    }
}