package com.example.server1.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "custom-config")
@Getter
@Setter
public class CustomConfig {
    public String name;
    public Map<String, String> objectData;
    public List<String> listData;
}
