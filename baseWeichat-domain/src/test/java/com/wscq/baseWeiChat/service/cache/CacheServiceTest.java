package com.wscq.baseWeiChat.service.cache;

import com.wscq.baseWeiChat.domain.model.Account;
import com.wscq.baseWeiChat.domain.service.cache.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zs on 16/9/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class CacheServiceTest {

    @Inject
    private CacheService cacheService;

    @Test
    public void testFist() {
        Account account = new Account();
        account.setUserName("zhangshuai");
        account.setPassword("123");
        cacheService.save("user", account, 10);
        Account getAc = (Account) cacheService.get("user");
        System.out.println("对象的测试:" + getAc.getUserName());
        getAc.setAccountType("大牛");

        List list = new ArrayList<>();

    }

    @Test
    public void testFSDFSD() {
        cacheService.delete("user");
        Account a = (Account) cacheService.get("user");
        System.out.println(a);
    }

    /**
     * 缓存性能读写测试
     */
    @Test
    public void testFSD() {
        for (int i = 0; i < 1000000; i++) {
            cacheService.save("key" + i, "value" + i);
        }
    }

    @Test
    public void testJGDIHGFIOWESFN() {
        long t1 = System.currentTimeMillis();
        String str = (String)cacheService.get("key999999");
        System.out.println(str);
        str = (String)cacheService.get("key123");
        System.out.println(str);
        str = (String)cacheService.get("key213");
        System.out.println(str);
        str = (String)cacheService.get("key32");
        System.out.println(str);
        str = (String)cacheService.get("key432");
        System.out.println(str);
        str = (String)cacheService.get("key32");
        System.out.println(str);
        str = (String)cacheService.get("key123123");
        System.out.println(str);
        str = (String)cacheService.get("key3123");
        System.out.println(str);
        str = (String)cacheService.get("key656");
        System.out.println(str);
        str = (String)cacheService.get("key54");
        System.out.println(str);
        str = (String)cacheService.get("key6543");
        System.out.println(str);
        str = (String)cacheService.get("key5345");
        System.out.println(str);
        str = (String)cacheService.get("key6542");
        System.out.println(str);
        str = (String)cacheService.get("key123");
        System.out.println(str);
        str = (String)cacheService.get("key11");
        System.out.println(str);
        str = (String)cacheService.get("key12312");
        System.out.println(str);
        str = (String)cacheService.get("key99991");
        System.out.println(str);
        str = (String)cacheService.get("key9992342");
        System.out.println(str);
        str = (String)cacheService.get("key352342");
        System.out.println(str);
        str = (String)cacheService.get("key343534");
        System.out.println(str);
        str = (String)cacheService.get("key343534");
        System.out.println(str);
        str = (String)cacheService.get("key343549");
        System.out.println(str);
        str = (String)cacheService.get("key343");
        System.out.println(str);
        str = (String)cacheService.get("key34");
        System.out.println(str);
        str = (String)cacheService.get("key34324");
        System.out.println(str);
        str = (String)cacheService.get("key123");
        System.out.println(str);

        long t2 = System.currentTimeMillis();
        System.out.println("time is:" + (t2-t1));
    }
}
