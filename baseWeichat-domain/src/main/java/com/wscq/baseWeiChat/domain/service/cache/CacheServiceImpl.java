package com.wscq.baseWeiChat.domain.service.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by zs on 16/9/27.
 */
// TODO 需要测试这样搞是否能支持所有的对象类型
@Service
public class CacheServiceImpl implements CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Inject
    private RedisSourceService redisSourceService;

    @Override
    public void save(String key, Serializable obj, int time) {
        ShardedJedis shardedJedis = redisSourceService.getRedisClient();

    }

    @Override
    public Object getString(String key) {
        return null;
    }

    @Override
    public boolean deleteCache(String key) {
        return false;
    }
}
