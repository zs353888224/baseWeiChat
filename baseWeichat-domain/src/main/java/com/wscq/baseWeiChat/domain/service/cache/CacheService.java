package com.wscq.baseWeiChat.domain.service.cache;

import java.io.Serializable;

/**
 * 使用缓存的hash存储方式(支持存储各种对象)
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
    void save(String key, Serializable obj, Integer time);

    void save(String key, Serializable obj);

    Object get(String key);

    boolean delete(String key);
}
