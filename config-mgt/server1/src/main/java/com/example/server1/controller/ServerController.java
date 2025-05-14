package com.example.server1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server1.config.CustomConfig;
import com.example.server1.fiegnClient.CloudFuncFeignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/s1")
@RefreshScope
public class ServerController {
    @Autowired
    public CustomConfig customConfig;

    @GetMapping("/custom-config")
    public ResponseEntity<?> getCustomConfigRecord() {
        return ResponseEntity.status(HttpStatus.OK).body(customConfig);
    }

    @GetMapping("/build-info")
    @CircuitBreaker(name = "route-circuitbreaker", fallbackMethod = "fallbackMethod")
    public String performOperation(HttpServletRequest req) throws InterruptedException {
        System.out.println(req.getHeaderNames());
        Thread.sleep(3000);
        if (Math.random() < 0.5) {
            throw new RuntimeException("Exception Happened");
        }
        return buildInfo;
    }

    public String fallbackMethod(Exception e) {
        // Fallback logic when circuit is open
        return "Fallback response due to: " + e.getMessage();
    }

    @Value("${appinfo.build.version}")
    private String buildInfo;

    @Autowired
    public CloudFuncFeignClient cloudFuncFeignClient;

    @GetMapping("/build-info-all")
    public ResponseEntity<Map<String, String>> getCloudServerBuildInfo() {
        Map<String, String> buildInfoMap = new HashMap<String, String>();
        buildInfoMap.put("server1", buildInfo);
        buildInfoMap.put("cloudfunc", cloudFuncFeignClient.getBuildInfo().getBody().toString());
        return ResponseEntity.status(HttpStatus.OK).body(buildInfoMap);
    }

}
