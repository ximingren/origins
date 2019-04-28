package com.D5.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

/**
 * redis连接池类
 */
@Component
public class RedisCache {
    @Autowired
    private Pool<Jedis> jedisPool;//redis连接池
    private Jedis jedis;//操作redis的实体类
    private Logger logger= Logger.getLogger("RedisCache");

    /**
     * 持久化数据,序列化
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        jedis = jedisPool.getResource();//获取连接池的资源
        try {
            jedis.set(key, value);//持久化
        } catch (Exception e) {
            logger.info("redis连接发生错误！");
        }
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        try {
            return jedis.get(key);
        } catch (Exception e) {
            logger.info("redis连接发生错误！");
        }
        return null;
    }
}
