package com.wscq.baseWeiChat.domain.service.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.inject.Inject;

/**
 * Created by zs on 16/9/27.
 */
@Service
public class RedisSourceServiceImpl implements RedisSourceService {

    public static final Logger logger = LoggerFactory.getLogger(RedisSourceServiceImpl.class);

    @Inject
    private ShardedJedisPool shardedJedisPool;


    @Override
    public ShardedJedis getRedisClient() {
        try{
            return shardedJedisPool.getResource();
        } catch (Exception e) {
            logger.error("get redis client error: {}", e);
        }
        return null;
    }

    @Override
    public void returnResource(ShardedJedis jedis) {
        if (jedis != null){
            jedis.close();
        }
    }
}
