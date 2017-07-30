package org.soaframe.web.rpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;

@Configuration
public class DubboConfig {

	public static final String APPLICATION_NAME = "soaframe-consumer";

	public static final String REGISTRY_ADDRESS = "zookeeper://127.0.0.1:2181";

	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(APPLICATION_NAME);
		return applicationConfig;
	}

	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress(REGISTRY_ADDRESS);
		return registryConfig;
	}

}
