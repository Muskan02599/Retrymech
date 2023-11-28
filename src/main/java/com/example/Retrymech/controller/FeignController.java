package com.example.Retrymech.controller;

import com.example.Retrymech.util.FeignServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class FeignController {
    @Autowired
    public FeignServiceUtil feignServiceUtil;
    Logger  log= LoggerFactory.getLogger(FeignController.class);
    int i=0;
    @GetMapping("/user-name")
    //by default will try to call 3 times after every 1 second, can be class level or method level
    //2000 2sec multiplier=2000*m delay
    @Retryable(maxAttempts = 5, backoff = @Backoff(delay =2000, multiplier = 2))
    public String getUserName(){
        log.info(++i + " call inside getName Method");
        return feignServiceUtil.getName();
    }
    @GetMapping("/user-address")
    public String getUserAddress(){
        return feignServiceUtil.getAddress();
    }
    @GetMapping("/user-status")
    public String getUserStatus(){
        return feignServiceUtil.getStatus();
    }
    // Recover is used when max attempt reached, can see in page after max attempts
    @Recover
    public String getRecoveryMessage(){
        return "Please check whether service is up or not";
    }
}
