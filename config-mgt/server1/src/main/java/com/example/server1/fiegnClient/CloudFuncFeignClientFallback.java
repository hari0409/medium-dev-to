package com.example.server1.fiegnClient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class CloudFuncFeignClientFallback implements CloudFuncFeignClient {
    @GetMapping("/api/c1/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error On Processing Request, Try again soon");
    }

}
