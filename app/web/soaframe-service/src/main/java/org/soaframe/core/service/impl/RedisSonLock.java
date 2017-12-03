package org.soaframe.core.service.impl;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * redisson三方工具包帮助类
 * 
 * @author zouhao
 * @date 2017年12月3日 下午2:02:21
 * 
 */
public class RedisSonLock {

	private static Config config = new Config();

	// 2. Create Redisson instance
	RedissonClient redisson = Redisson.create(config);

	public static RedissonClient getRedissonClient(Config config) {

		return Redisson.create(config);
	}

}
