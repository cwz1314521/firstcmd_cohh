package com.hema.newretail.backstage.common.caches;


import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * cloud_erp-com.hema.newretail.erp.common.caches.mybatis
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-17 15:54
 */
public class MybatisRedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;
    private RedisTemplate redisTemplate;
    private static final long EXPIRE_TIME_IN_MINUTES = 30;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Put query result to redis
     *
     * @param key
     * @param value
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        CacheKey cacheKey = (CacheKey) key;
        opsForValue.set(cacheKey.toString(), value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
        logger.debug("Put query result to redis");
    }

    /**
     * Get cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        logger.debug("Get cached query result from redis");
        CacheKey cacheKey = (CacheKey) key;
        return opsForValue.get(cacheKey.toString());
    }

    /**
     * Remove cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object removeObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        CacheKey cacheKey = (CacheKey) key;
        redisTemplate.delete(cacheKey.toString());
        logger.debug("Remove cached query result from redis");
        return null;
    }

    /**
     * Clears this cache instance
     */
    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
        logger.debug("Clear all the cached query result from redis");
    }

    @Override
    public int getSize() {
        Long size = (Long) getRedisTemplate().execute((RedisCallback<Long>) RedisServerCommands::dbSize);
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextUtil.getBean("genericRedisTemplate");
        }
        return redisTemplate;
    }

}
