package com.example.server1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server1.config.CustomConfig;

@RestController
@RequestMapping(value = "/api/s1")
@RefreshScope
public class ServerController {
    @Autowired
    public CustomConfig customConfig;

    @Value("${appinfo.build.version}")
    private String buildInfo;

    @GetMapping("/custom-config")
    public ResponseEntity<?> getCustomConfigRecord() {
        return ResponseEntity.status(HttpStatus.OK).body(customConfig);
    }

    @GetMapping("/build-info")
    public ResponseEntity<?> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildInfo);
    }

}
