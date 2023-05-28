package com.in28minutes.springboot;

import java.util.HashMap;
import java.util.Map;

import com.in28minutes.springboot.service.ExternalApiCalls;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.configuration.BasicConfiguration;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService service;

    @Autowired
    private BasicConfiguration configuration;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExternalApiCalls externalapicalls;




    @RequestMapping("/welcome")
//    @RateLimiter(name="stockService", fallbackMethod = "getFallbackStocks")
    public ResponseEntity<String> welcome(){

        try {
//            Thread.sleep(2000);

//            for (int i = 0; i < 10; i++) {
//                System.out.println("calling count" + i);
              String s=  externalapicalls.ExternalAPI();
//            }
//            return externalapicalls.ExternalAPI();
            if(!(s.contains("fallbackcalls")))
            return new ResponseEntity<>(s, HttpStatus.OK);
            else
                return new ResponseEntity<>(s, HttpStatus.EXPECTATION_FAILED);
        } catch (InterruptedException e1) {
            return new ResponseEntity<>("InterruptedException"+ e1.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
        }
         catch (Exception e){
            return new ResponseEntity<>("Hello World! Exception", HttpStatus.EXPECTATION_FAILED);
        }
//        return ExternalApiCalls.ExternStaticalCall();
    }

    @RequestMapping("/dynamic-configuration")
    public Map<String, Object> dynamicConfiguration() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", configuration.getMessage());
        map.put("number", configuration.getNumber());
        map.put("value", configuration.isValue());

        return map;
    }

    public String getFallbackStocks(String request, RequestNotPermitted rnp) {
        // fetch results from the cache
        System.out.printf("error captured--SK ----" + rnp.getMessage());
        return request;
    }

}
