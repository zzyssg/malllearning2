package com.zzy.mall.redis.service;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName RedisService
 * @Author ZZy
 * @Date 2024/5/22 22:49
 * @Description
 * @Version 1.0
 */

public interface RedisService {

    /**
     * 普通value
     */

    //保存value
    void set(String key,Object value,Long time);

    void set(String key, Object value);

    //删除
    Boolean del(String key);

    Long del(List<String> keys);

    //查询
    Object get(String key);

    Long deleteBatch(List<String> keys);

    //设置过期时间
    Boolean expire(String key, Long time);

    //获取过期时间
    Long getExpire(String key);

    //判断是否存在
    Boolean hashKey(String key);

    //根据delta递增
    Long incr(String key, long delta);

    //根据delta递减
    Long decr(String key, long delta);


    /**
     * Hash value
     */

    //设置value TODO 下一个hSet为什么不返回Boolean
    Boolean hSet(String key, String hashKey,Object value, long time);

    void hSet(String key, String hashKey,Object value);

    //设置整个Hash结构
    Boolean hSetAll(String key, Map<String, Object> map, long time);

    void hSetAll(String key, Map<String, Object> map);

    //删除Hash结构的值
    void hDelete(String key, Object... hashKey);

    //获取value
    Object hGet(String key, String hashKey);

    //获取整个Hash结构
    Map<Object,Object> hGetAll(String key);

    //Hash结构中是否含有该属性
    Boolean hHashKey(String key, String hashKey);

    //根据delta递增/递减
    void hIncr(String key, String hashKey, Long delta);

    void hDecr(String key, String hashKey, Long delta);


    /**
     * Set value
     */

     //set结构中添加属性
    Long sAdd(String key, long time,Object... values);

    Long sAdd(String key, Object... values);

    //删除set结构中的属性
    Long sRemove(String key, Object... values);

    //获取Set结构
    Set<Object> sMembers(String key);

    //Set结构中是否含有该属性
    Boolean sIsNumber(String key, Object value);

    //获取set的长度
    Long sSize(String key);

    /**
     * List value
     */

    //向List结构中添加属性
    Long lPush(String key, Object value, long time);

    Long lPush(String key, Object value);

    //向List结构中批量添加属性
    Long lPushAll(String key, long time, Object... values);

    Long lPushAll(String key, Object... values);

    //移除属性
    Long lRemove(String key, int count, Object value);

    //查询属性
    List<Object> lRange(String key, long start, long end);

    //根据索引获取属性
    Object lIndex(String key, long index);

    Long lSize(String key);




}
