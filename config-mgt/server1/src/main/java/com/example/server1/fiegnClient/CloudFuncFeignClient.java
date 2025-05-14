package com.example.server1.fiegnClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cloudfunc", fallback = CloudFuncFeignClientFallback.class)
public interface CloudFuncFeignClient {
    @GetMapping("/api/c1/build-info")
    public ResponseEntity<String> getBuildInfo();
}
