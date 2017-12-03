package org.soaframe.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * redis通用服务
 * 
 * @author zouhao
 * 
 * @date 2017年7月11日 下午8:31:06
 */
@Service
public class RedisLock {

	private static final String LOCKED = "locked";

	@Autowired
	private RedisService redisService;

	/**
	 * 分布式加锁
	 * 
	 * @author zouhao
	 * @param key
	 * @param lockTime
	 *            获取锁超时时间
	 * @param expireTime
	 *            锁有效最长时间
	 */
	public void lock(String key, long lockTime, long expireTime) {
		long nanoTime = System.nanoTime();
		lockTime *= 1000000000;// 换算成微秒数
		try {
			// 在timeout的时间范围内不断轮询锁
			while (System.nanoTime() - nanoTime < lockTime) {
				System.out.println("-------");
				// 锁不存在的话，设置锁并设置锁过期时间，即加锁
				if (!redisService.setNX(key, LOCKED, expireTime)) {
					System.out.println("出现锁等待");
					// 短暂休眠，避免可能的活锁
					Thread.sleep(1000);
				} else {
					return;
				}
			}
			throw new RuntimeException("Require lock timeout");
		} catch (Exception e) {
			throw new RuntimeException("Require lock exception", e);
		}
	}

	/**
	 * 释放锁
	 * 
	 * @author zouhao
	 * @param key
	 */
	public void unlock(String key) {
		redisService.delKey(key);
	}

}
