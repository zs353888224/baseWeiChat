package com.wscq.baseWeiChat.repository;

import com.wscq.baseWeiChat.domain.model.mybatis.gen.TUser;
import com.wscq.baseWeiChat.domain.repository.mybatis.TUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zs on 16/10/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class mybatisTest {

    @Inject
    TUserMapper tUserMapper;

    @Test
    public void testFSD(){
        List<TUser> users = tUserMapper.queryAll2();
    }

    @Test
    public void testFSDFSDD(){
        TUser tUser = tUserMapper.queryByPrimaryKey(1);
    }
}
