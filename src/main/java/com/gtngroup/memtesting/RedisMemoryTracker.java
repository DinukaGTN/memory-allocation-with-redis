package com.gtngroup.memtesting;

import org.springframework.stereotype.Component;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

@Component
public class RedisMemoryTracker {

    private static final long MB_IN_BYTES = 1024 * 1024; // 1MB in Bytes

    private final RedisTemplate<String, String> redisTemplate;

	public RedisMemoryTracker(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

    public void trackMemoryUsage() {
		long totalMemory = 0L;
		int count = 1;
		while (count <= (1024 * 1024)) { // insert 1,048,576 elements to the redis list
			String randomUUID = UUID.randomUUID().toString();
//			System.out.println(counter + ": " + randomUUID);
			redisTemplate.opsForList().rightPush("my-uuid-list", randomUUID);
			totalMemory += randomUUID.length();
			count++;
		}
		System.out.println("Memory usage: " + totalMemory + " bytes");
		System.out.println("Number of elements in the list: " + count);
    }
}