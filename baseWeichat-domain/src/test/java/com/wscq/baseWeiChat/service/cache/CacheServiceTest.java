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
    public void testFist(){
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
    public void testFSDFSD(){
        cacheService.delete("user");
        Account a = (Account) cacheService.get("user");
        System.out.println(a);
    }
}
