package com.athen.redis.service.impl;
import com.athen.redis.service.IRedisService;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by foresee on 2019-03-06.
 */
public class RedisServiceImpl implements IRedisService {
	
    private RedisTemplate<String, Object> redisTemplate;

    public RedisServiceImpl() {
    }

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置字符串类型缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void setString(String key, String value) {
         redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 设置字符串类型缓存并设置过期时间
     *
     * @param key
     * @param value
     * @param expire 过期时间，单位：秒
     */
    @Override
    public void setString(String key, String value, TimeUnit expire) {
         redisTemplate.opsForValue().set(key,value,3000,expire);
    }

    /**
     * 设置对象类型缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void setObject(String key, Object value) {

    }

    /**
     * 设置对象类型缓存并设置过期时间
     *
     * @param key
     * @param value
     * @param expire
     */
    @Override
    public void setObject(String key, Object value, TimeUnit expire) {

    }

    /**
     * 方法重载
     * {@link #setObject(String, java.io.Serializable, int)}
     *
     * @param key
     * @param value
     * @param expire
     * @param cover  false：有则不覆盖，无则添加; true:无论有无均覆盖
     */
    @Override
    public void setObject(String key, Object value, TimeUnit expire, boolean cover) {

    }

    /**
     * 读取字符串类型缓存
     *
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        return null;
    }

    /**
     * 读取字符串类型缓存并设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    @Override
    public String getString(String key, TimeUnit expire) {
        return null;
    }

    /**
     * 读取对象类型缓存
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(String key) {
        return null;
    }

    /**
     * 读取对象型缓存并设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    @Override
    public Object getObject(String key, TimeUnit expire) {
        return null;
    }

    /**
     * 根据key删除缓存内容
     *
     * @param key
     */
    @Override
    public void removeObject(String key) {

    }

    /**
     * 设置缓存过期时间
     *
     * @param key
     * @param expire
     */
    @Override
    public void expire(String key, TimeUnit expire) {

    }

    /**
     * 返回当前选定数据库中的key数量
     *
     * @return
     */
    @Override
    public long getRedisSize() {
        return 0;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean exists(String key) {
        return false;
    }

    /**
     * 使用哈希结构存储设置对象类型缓存并设置过期时间
     *
     * @param key
     * @param field
     * @param value
     * @param expire
     */
    @Override
    public void setHashObject(String key, String field, Object value, TimeUnit expire) {

    }

    /**
     * 使用哈希结构存储设置Map缓存并设置过期时间
     *
     * @param key
     * @param values
     * @param expire
     */
    @Override
    public <T> void setHashObject(String key, Map<String, T> values, int expire) {

    }

    /**
     * 使用哈希结构存储设置Map缓存并设置过期时间（适用大数据量存储）
     *
     * @param key
     * @param values
     * @param expire
     */
    @Override
    public void setHashObjectWithPiple(String key, Map<String, Object> values, int expire) {

    }

    /**
     * 读取哈希结构中某个field的内容
     *
     * @param key
     * @param field
     * @return
     */
    @Override
    public Object getHashObject(String key, String field) {
        return null;
    }

    /**
     * 方法重载
     * {@link #getHashObject(String, String)}
     * <pre>
     * 尝试读取数据，当第一次读取不到数据时，再尝试线程休眠，根据休眠时间再次读取数据
     * 使用场景：取数据的时候数据没有，正在入库，变更码表
     * </pre>
     *
     * @param key
     * @param field
     * @param threadSleep 单位：毫秒
     * @return
     */
    @Override
    public Object getHashObject(String key, String field, int threadSleep) {
        return null;
    }

    /**
     * 读取哈希结构中所有values值
     *
     * @param key
     * @return
     */
    @Override
    public List<?> getHashObject(String key) {
        return null;
    }

    /**
     * 根据key值获取整个HashMap对象
     *
     * @param key
     * @return
     */
    @Override
    public Map<Object, Object> getHashMap(String key) {
        return null;
    }

    /**
     * 根据key和 模糊匹配的field查询hash对象
     *
     * @param key
     * @param fieldMatch 模糊匹配的field
     * @param count      需要返回结果集个数
     * @return
     */
    @Override
    public List<?> scanHashData(String key, String fieldMatch, long count) {
        return null;
    }

    /**
     * 移除哈希结构中的某个field内容
     *
     * @param key
     * @param field
     */
    @Override
    public void removeHashObject(String key, String field) {

    }

    /**
     * 数组存放队列中
     *
     * @param key
     * @param models
     * @param expire
     */
    @Override
    public <T> boolean leftPushAll(String key, List<T> models, int expire) {
        return false;
    }

    /**
     * 单独放入对队列中
     * value为对象类型，且不设置过期时间，默认永久
     *
     * @param key
     * @param value
     * @param expire
     */
    @Override
    public boolean leftPush(String key, Object value, int expire) {
        return false;
    }

    /**
     * 获取某个队列中元素值
     * *
     *
     * @param key
     */
    @Override
    public long size(String key) {
        return 0;
    }

    /**
     * 弹出最右边的元素，弹出之后该值在列表中将不复存在
     *
     * @param key
     */
    @Override
    public <T> T rightPop(String key) {
        return null;
    }

    /**
     * 根据index获取list值 *
     *
     * @param key
     * @param index
     */
    @Override
    public <T> T index(String key, long index) {
        return null;
    }

    /**
     * @param key
     */
    @Override
    public void delete(String key) {

    }
}
