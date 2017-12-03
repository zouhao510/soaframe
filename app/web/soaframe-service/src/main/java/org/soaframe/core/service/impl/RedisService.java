package org.soaframe.core.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 * redis通用服务
 * 
 * @author zouhao
 * 
 * @date 2017年7月11日 下午8:31:06
 */
@Service
public class RedisService {

	public static final String BILLCENTER_CACHE_KEY = "soaframe:%s";// 为缓存的key添加默认的前缀:
	public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.HOURS;
	public static final long DEFAULT_CACHE_TIME = 1L;
	public static final long WARN_CACHE_TIME = 24 * 30L;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * @param key
	 * @return
	 * @Description:key是否存在
	 * @author zouhao
	 */
	public boolean existKey(String key) {
		return redisTemplate.hasKey(String.format(BILLCENTER_CACHE_KEY, key));
	}

	/**
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 * @Description:设置key过期时间
	 * @author zouhao
	 */
	public boolean expireKey(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(String.format(BILLCENTER_CACHE_KEY, key), timeout, unit);
	}

	/**
	 * @param key
	 * @param value
	 * @Description: redis缓存key-value
	 * @author zouhao
	 */
	public void opsForValueSet(String key, Object value) {
		redisTemplate.opsForValue().set(String.format(BILLCENTER_CACHE_KEY, key), value);
	}

	/**
	 * @param key
	 * @param value
	 * @param timeout
	 * @param unit
	 * @Description:redis缓存key-value,并设置key过期时间
	 * @author zouhao
	 */
	public void opsForValueSetWithExpire(String key, Object value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(String.format(BILLCENTER_CACHE_KEY, key), value, timeout, unit);
	}

	/**
	 * @param key
	 * @return
	 * @Description:通过key获取value
	 * @author zouhao
	 */
	public Object opsForValueGet(String key) {
		return redisTemplate.opsForValue().get(String.format(BILLCENTER_CACHE_KEY, key));
	}

	/**
	 * @param key
	 * @param delta
	 * @return
	 * @Description:通过key原子叠加
	 */
	public Object opsForValueAdd(String key, long timeout, TimeUnit unit, long delta) {
		if (!redisTemplate.hasKey(key)) {
			Number result = redisTemplate.opsForValue().increment(String.format(BILLCENTER_CACHE_KEY, key), delta);
			redisTemplate.expire(String.format(BILLCENTER_CACHE_KEY, key), timeout, unit);
			return result;
		}
		return redisTemplate.opsForValue().increment(String.format(BILLCENTER_CACHE_KEY, key), delta);
	}

	/**
	 * @Description:通过key原子叠加
	 * @param key
	 * @param delta
	 * @return
	 */
	public Object opsForIncrement(String key, int delta) {
		if (!redisTemplate.hasKey(String.format(BILLCENTER_CACHE_KEY + key))) {
			return null;
		}
		return redisTemplate.opsForValue().increment((String.format(BILLCENTER_CACHE_KEY + key)), delta);
	}

	/**
	 * @Description:删除key
	 * @param key
	 */
	public void delKey(String key) {
		redisTemplate.delete((String.format(BILLCENTER_CACHE_KEY, key)));
	}

	/**
	 * 用于分布式锁,key不存在直接设置值,存在则不作任何操作
	 * 
	 * set value if not exist
	 * 
	 * @author zouhao
	 * @param key
	 * @param value
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Boolean setNX(String key, Object value, long expire) {
		RedisConnection conn = redisTemplate.getConnectionFactory().getConnection();
		RedisSerializer keySerializer = redisTemplate.getKeySerializer();
		RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
		byte[] keyByte = keySerializer.serialize((String.format(BILLCENTER_CACHE_KEY, key)));
		Boolean result = conn.setNX(keyByte, valueSerializer.serialize(value));
		if (result) {
			// 设置默认超时时间1min,防止锁不释放
			conn.expire(keyByte, expire);
		}
		return result;
	}
}
