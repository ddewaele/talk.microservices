package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@SpringBootApplication
@EnableFeignClients
@RestController
public class StaticClient1 {

	@Inject
	DataClient dataClient;

	public static void main(String[] args) {
		SpringApplication.run(StaticClient1.class, args);
	}

	@RequestMapping("/client")
	public Data client() {
		return dataClient.data();
	}
}
