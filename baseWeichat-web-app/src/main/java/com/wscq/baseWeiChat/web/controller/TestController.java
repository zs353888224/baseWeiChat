package com.wscq.baseWeiChat.web.controller;

import com.wscq.baseWeiChat.domain.service.cache.CacheService;
import com.wscq.baseWeiChat.web.controller.dto.APIResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zs on 16/9/29.
 */
@Controller
@RequestMapping("/public")
public class TestController {

    @Inject
    private CacheService cacheService;

    @RequestMapping("/test")
    public void testSDA(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = "hello redis!";
        APIResult apiResult = APIResult.success().setStatus(1);
        cacheService.save("test", str, 10);
        cacheService.save("api", apiResult, 10);

        String str1 = (String) cacheService.get("test");
        APIResult str2 = (APIResult) cacheService.get("api");

        cacheService.delete("test");

        String sss = (String) cacheService.get("test");

        response.getWriter().append("str is:").append(str1).append("\nobject is:" + str2.getStatus()).append("\ntest delete:").append(sss).flush();

    }

}
