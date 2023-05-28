package com.in28minutes.springboot;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.Duration;

@SpringBootApplication
public class StudentApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(StudentApplication.class, args);
        RateLimiterConfig config = RateLimiterConfig.custom()
//                .limitRefreshPeriod(Duration.ofMillis(5000))
                .limitForPeriod(1)
                .timeoutDuration(Duration.ofNanos(1000000000))
                .build();

// Create registry
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);

// Use registry
        RateLimiter rateLimiterWithDefaultConfig = rateLimiterRegistry
                .rateLimiter("name1");

        RateLimiter rateLimiterWithCustomConfig = rateLimiterRegistry
                .rateLimiter("name2", config);

        // System.out.println(ctx);
    }
}