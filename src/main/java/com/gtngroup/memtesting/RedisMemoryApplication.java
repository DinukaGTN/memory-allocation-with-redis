package com.gtngroup.memtesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RedisMemoryApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RedisMemoryApplication.class, args);

		RedisMemoryTracker memoryTracker = context.getBean(RedisMemoryTracker.class);
		memoryTracker.trackMemoryUsage();
	}

}
