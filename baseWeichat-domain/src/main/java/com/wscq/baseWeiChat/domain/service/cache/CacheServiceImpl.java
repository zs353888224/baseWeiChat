package com.wscq.baseWeiChat.domain.service.cache;

import com.wscq.baseWeiChat.domain.constants.SystemConstants;
import com.wscq.baseWeiChat.domain.util.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by zs on 16/9/27.
 */
@Service
public class CacheServiceImpl implements CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Inject
    private RedisSourceService redisSourceService;

    @Override
    public void save(String key, Serializable obj, Integer time) {
        ShardedJedis shardedJedis = redisSourceService.getRedisClient();
        shardedJedis.hsetnx(SystemConstants.APP_GLOBAL_VARIABLE.getBytes(), key.getBytes(), SerializeUtils.serializeObject(obj));
        // TODO 将数据的有效时间添加进去
        redisSourceService.returnResource(shardedJedis);
    }

    @Override
    public Object get(String key) {
        ShardedJedis shardedJedis = redisSourceService.getRedisClient();
        byte[] obj = shardedJedis.hget(SystemConstants.APP_GLOBAL_VARIABLE.getBytes(), key.getBytes());
        redisSourceService.returnResource(shardedJedis);
        return SerializeUtils.deserializeObject(obj);
    }

    @Override
    public boolean delete(String key) {
        ShardedJedis shardedJedis = redisSourceService.getRedisClient();
        Long rs;
        rs = shardedJedis.hdel(SystemConstants.APP_GLOBAL_VARIABLE.getBytes(), key.getBytes());
        redisSourceService.returnResource(shardedJedis);
        return rs >= 0;
    }
}
