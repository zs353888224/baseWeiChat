package com.wscq.baseWeiChat.domain.repository.mybatis;

import com.wscq.baseWeiChat.domain.model.mybatis.gen.TUser;
import com.wscq.baseWeiChat.domain.repository.CrudMapper;
import com.wscq.baseWeiChat.domain.repository.MybatisMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zs on 16/10/18.
 */
@MybatisMapper
public interface TUserMapper extends CrudMapper<TUser, Integer> {

    /**
     * 常规配置XML的用法
     *
     * @return
     */
    TUser queryByPrimaryKey(Integer id);

    /**
     * 简洁版注释用法
     *
     * @return
     */
    @Select("select * from t_user")
    @ResultMap("BaseResultMap")
    List<TUser> queryAll2();
}
