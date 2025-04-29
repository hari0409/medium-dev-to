package com.example.server1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server1.config.CustomConfig;
import com.example.server1.config.StudentDataDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/s1")
public class ServerController {

    @Autowired
    public StreamBridge streamBridge;

    @Autowired
    public CustomConfig customConfig;

    @GetMapping("/custom-config")
    public ResponseEntity<?> getCustomConfigRecord() {
        return ResponseEntity.status(HttpStatus.OK).body(customConfig);
    }

    @PostMapping("/send/data")
    public ResponseEntity<?> sendEventData(@RequestBody StudentDataDto data) {
        streamBridge.send("student-data", data);
        return ResponseEntity.status(HttpStatus.OK).body("Event Sent to the other service");
    }

}
