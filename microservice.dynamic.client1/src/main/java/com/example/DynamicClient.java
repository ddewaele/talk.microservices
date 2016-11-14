package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients // Needed otherwise Dataclient cannot be injected.
//@EnableDiscoveryClient
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class DynamicClient {

	public static void main(String[] args) {
		SpringApplication.run(DynamicClient.class, args);
	}

}
