package org.soaframe.core.service;

import java.util.concurrent.TimeUnit;

public interface DistributeLock {

	void lock(String lockKey);

	void unlock(String lockKey);

	void lock(String lockKey, int timeout);

	void lock(String lockKey, TimeUnit unit, int timeout);

}
