package com.wscq.baseWeiChat.web.controller;

import com.wscq.baseWeiChat.domain.model.mybatis.gen.TUser;
import com.wscq.baseWeiChat.domain.repository.mybatis.TUserMapper;
import com.wscq.baseWeiChat.domain.service.cache.CacheService;
import com.wscq.baseWeiChat.web.controller.dto.APIResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by zs on 16/9/29.
 */
@Controller
@RequestMapping("/public")
public class TestController {

    @Inject
    private CacheService cacheService;

    @Inject
    private TUserMapper tUserMapper;

    /**
     * 测试controller是单列的
     */
    private int key = 0;

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

    @RequestMapping("/testMap")
    public String testMap(HttpServletRequest request, Model model) {
        return "test/test_map";
    }

    @RequestMapping("/testsql")
    @ResponseBody
    public String testMybatis() {
        TUser tUser = tUserMapper.queryByPrimaryKey(1);
        List<TUser> list = tUserMapper.queryAll2();
        return "hello mybatis";
    }

    @RequestMapping("/testSet")
    @ResponseBody
    public String testIsPrototype() {
        return "this is set :" + this.key++;
    }

    @RequestMapping("/testRead")
    @ResponseBody
    public String testIsPrototype2() {
        return "this is read :" + key;
    }

    @RequestMapping("/testJs")
    public String testFSDFSF() {
        return "test/wechatJsTest";
    }

    @RequestMapping("/testAjax")
    @ResponseBody
    public APIResult testGSDDS(HttpServletRequest request) {
        return APIResult.success();
    }
}
