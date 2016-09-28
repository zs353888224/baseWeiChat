package com.wscq.baseWeiChat.domain.service.cache;

import redis.clients.jedis.ShardedJedis;

/**
 * redis连接池
 *
 * Created by zs on 16/9/27.
 */
public interface RedisSourceService {

    ShardedJedis getRedisClient();

    void returnResource(ShardedJedis jedis);
}
