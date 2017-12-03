package org.soaframe.core.service.impl;

import java.util.concurrent.TimeUnit;

import org.soaframe.core.service.DistributeLock;

/**
 * redis分布式锁帮助类
 * 
 * @author zouhao
 * @date 2017年12月3日 下午3:39:46
 * 
 */
public class RedissLockUtil {

	private static DistributeLock redissLock;

	public static void setLocker(DistributeLock locker) {
		redissLock = locker;
	}

	public static void lock(String lockKey) {
		redissLock.lock(lockKey);
	}

	public static void unlock(String lockKey) {
		redissLock.unlock(lockKey);
	}

	/**
	 * 带超时的锁
	 * 
	 * @param lockKey
	 * @param timeout
	 *            超时时间 单位：秒
	 */
	public static void lock(String lockKey, int timeout) {
		redissLock.lock(lockKey, timeout);
	}

	/**
	 * 带超时的锁
	 * 
	 * @param lockKey
	 * @param unit
	 *            时间单位
	 * @param timeout
	 *            超时时间
	 */
	public static void lock(String lockKey, TimeUnit unit, int timeout) {
		redissLock.lock(lockKey, unit, timeout);
	}
}