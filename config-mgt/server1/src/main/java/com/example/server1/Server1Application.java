package com.example.server1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.example.server1.config.CustomConfig;

@SpringBootApplication
@EnableConfigurationProperties(value = { CustomConfig.class })
@EnableFeignClients
public class Server1Application {

	public static void main(String[] args) {
		SpringApplication.run(Server1Application.class, args);
	}

}
