package com.gtngroup.memtesting;

import org.springframework.stereotype.Component;

import org.springframework.data.redis.core.RedisTemplate;

@Component
public class RedisMemoryTracker {

    private static final long TARGET_MEMORY_BYTES = 1024 * 1024 * 1024; // 1GB

    private final RedisTemplate<String, String> redisTemplate;

	public RedisMemoryTracker(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

    public void trackMemoryUsage() {
		long totalMemory = 0L;
		int counter = 1;
		while (totalMemory < (TARGET_MEMORY_BYTES / 256)) { // 1GB in bytes
			String randomUUID = RandomUUIDGenerator.generateUUID();
			System.out.print(randomUUID);
			System.out.print(": ");
			System.out.println(counter);
			redisTemplate.opsForList().rightPush("uuid-list", randomUUID);
			totalMemory += randomUUID.length();
			counter++;
		}
		System.out.println("Memory limit reached: " + totalMemory + " bytes");
        // System.out.println("Number of elements in the list: " + stringList.size());
    }

    // private long getUsedMemory() {
    //     // Using MXBeans to estimate used heap memory
    //     Runtime runtime = Runtime.getRuntime();
    //     long usedMemory = runtime.totalMemory() - runtime.freeMemory();
    //     return usedMemory;
    // }
}