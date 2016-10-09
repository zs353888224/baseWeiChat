package com.wscq.baseWeiChat.service.weichat;

import com.wscq.baseWeiChat.domain.service.weichat.AccessTokenService;
import org.elasticsearch.common.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.net.MalformedURLException;

/**
 * Created by zs on 16/9/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class WeichatTest {

    @Inject
    private AccessTokenService weichatOAuthService;
    @Test
    public void testFSDF() throws MalformedURLException {
        String str = weichatOAuthService.getAccessToken();
        System.out.println("CBIiJdvZUxlM4qP0DBl45-SdZ-UOcw2pNc2gaehdTXpg2HIUsZbEu6mf1ThdFGTNtLVdV2SHVtF9SXffVpnWP63UCkV_i23ErGJbohDL9pDNTNxVC6bilGs98AzwLkZHDRYiAGATHO");
        System.out.println(str);
    }

    @Test
    public void testFSDFADADAD() {
        DateTime dateTime = new DateTime();
        DateTime dateTime1 = new DateTime(1475200744325l);
        System.out.println(dateTime);
        System.out.println(dateTime.plusHours(1).getMillis() > dateTime1.getMillis());
    }
}
