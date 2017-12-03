package org.soaframe.core.service.impl;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.soaframe.core.service.DistributeLock;

/**
 * redisson分布式锁实现
 * 
 * @author zouhao
 * @date 2017年12月3日 下午3:28:44
 * 
 */
public class RedisDistribuiteLocker implements DistributeLock {

	private RedissonClient redissonClient;

	@Override
	public void lock(String lockKey) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.lock();
	}

	@Override
	public void unlock(String lockKey) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.unlock();
	}

	@Override
	public void lock(String lockKey, int leaseTime) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.lock(leaseTime, TimeUnit.SECONDS);
	}

	@Override
	public void lock(String lockKey, TimeUnit unit, int timeout) {
		RLock lock = redissonClient.getLock(lockKey);
		lock.lock(timeout, unit);
	}

	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

}
