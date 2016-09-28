package com.wscq.baseWeiChat.domain.service.cache;

import java.io.Serializable;

/**
 * 使用redis提供的各种服务
 *
 * Created by zs on 16/9/27.
 */
public interface CacheService {

    /**
     * 存储对象, 可设置有效时间
     *
     * @param key
     * @param obj
     * @param time 有效时间
     * @return
     */
    void save(String key, Serializable obj, int time);

    Object getString(String key);

    boolean deleteCache(String key);
}
