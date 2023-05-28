package com.in28minutes.springboot.configuration;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties("basic")
public class BasicConfiguration {

    private boolean value;

    private String message;

    private int number;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
