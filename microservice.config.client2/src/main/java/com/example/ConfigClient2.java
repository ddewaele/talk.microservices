package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigClient2 {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClient2.class, args);
	}
}

@RestController
@RefreshScope
class MyRestController {

	@Value("${config.value}")
	private String value = "default";

	@Value("${config.super-secret-value}")
	private String secretValue = "default";

	@RequestMapping(path = "/value")
	public String getConfigValue() {
		return value;
	}

	@RequestMapping(path = "/secret-value")
	public String getSecretConfigValue() {
		return secretValue;
	}
}