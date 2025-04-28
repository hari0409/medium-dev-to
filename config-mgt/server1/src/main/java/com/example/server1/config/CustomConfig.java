package com.example.server1.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom-config")
public record CustomConfig(String name, Map<String, String> objectData, List<String> listData) {
}
