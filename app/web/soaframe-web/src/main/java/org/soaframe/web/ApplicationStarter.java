package org.soaframe.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: spring启动类
 * @author zouhao
 * @date 2017年7月9日 上午11:54:17
 * 
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.soaframe")
public class ApplicationStarter {

	public static void main(String[] args) {

		SpringApplication.run(ApplicationStarter.class, args);

	}

}
