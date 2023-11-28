package com.example.Retrymech.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "feigndemo",url = "http://localhost:8111/user")
public interface FeignServiceUtil {
    @GetMapping("/name")
      String getName();
    @GetMapping("/address")
      String getAddress();
    @GetMapping("/status")
      String getStatus();

}
