package com.in28minutes.springboot.service;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
//import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ExternalApiCalls {

    @Autowired
    private  RestTemplate restTemplate;


    @RateLimiter(name="rateLimiterApi" , fallbackMethod = "getFallbackStocks")
    public String ExternalAPI() throws InterruptedException {
        Integer iCount=0;
        try {
            Thread.sleep(2000);
            System.out.println("time making the call: " + LocalDateTime.now().toString());
            String s = restTemplate.getForObject("https://yahoo.com", String.class);
            System.out.println("normal processing");
            return "normal processing";
        } catch (Exception e){
           throw  e;
        }
    }
//    @RateLimiter(name="stockService", fallbackMethod = "getFallbackStocks")
//    public static  String ExternStaticalCall() throws InterruptedException {
//        Thread.sleep(2000);
//        String s= restTemplate.getForObject("https://yahoo.com",String.class);
//        return s;
//    }


    public String getFallbackStocks(String request, RequestNotPermitted rnp) {
        // fetch results from the cache
        System.out.printf("error captured--SK");
        return "SK fallbackcalls";
    }
}
