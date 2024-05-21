package com.zzy.mall.tiny.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName RedisService
 * @Author ZZy
 * @Date 2024/5/12 21:23
 * @Description 操作Redis
 * @Version 1.0
 */
public interface RedisService {


    /**
     * 普通值操作
     */
    void set(String key, Object value, long time);

    void set(String key, Object value);

    Object get(String key);

    Boolean del(String key);

    Long del(List<String> keys);

    //过期时间
    Boolean expire(String key, long time);

    Long getExpire(String key);

    Boolean hasKey(String key);

    //按delta递增
    Long incr(String key, long delta);

    Long dec(String key, long delta);

    /**
     * hash 结构操作
     */
    //获取hash结构中的属性
    Object hGet(String key, String hashKey);

    //向hash结构中设置属性
    Boolean hSet(String key, String hashKey, Object value, long time);

    void hSet(String key, String hashKey, Object value);

    //直接获取整个hash结构
    Boolean hSetAll(String key, Map<String, Object> map, long time);

    void hSetAll(String key, Map<String, Object> map);

    //删除Hash结构的属性
    void hDel(String key, Object... hashKey);

    //判断hash结构中是否有此属性
    Boolean hHasKey(String key, String hashKey);

    //hash结构中，属性递增/减
    Long hIncr(String key, String hashKey, long delta);

    Long hDec(String key, String hashKey, long delta);

    /**
     * set 结构操作
     */
    Set<Object> sMembers(String key);

    //set结构中添加属性
    Long sAdd(String key, long time, Object... values);

    Long sAdd(String key, Object... values);

    //是否为set中的属性
    Boolean sIsMember(String key, Object value);

    //获取set结构的长度
    Long sSize(String key);

    //删除set结构中的属性
    Long sRemove(String key, Object... values);

    /**
     * list 结构操作
     */

    //获取list结构的属性
    List<Object> lRange(String key, long start, long end);

    //获取list的长度
    Long lSize(String key);

    //根据索引获取list结构中的属性
    Object lIndex(String key, long index);

    //向list结构中添加属性
    Long lPush(String key, Object value);

    Long lPush(String key, Object value, long time);

    //向List结构中-批量-添加属性
    Long lPushAll(String key, Object... values);

    Long lPushAll(String key, long time,Object... values);

    //从 list 结构中移除属性
    Long lRemove(String key, long count, Object value);


}
