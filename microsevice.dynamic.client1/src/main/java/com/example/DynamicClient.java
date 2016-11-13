package com.example;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@EnableFeignClients // Needed otherwise Dataclient cannot be injected.
//@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@RestController
public class DynamicClient {

	@Inject
	DataClient dataClient;

	@Inject
	private EurekaClient eurekaClient;

	public static void main(String[] args) {
		SpringApplication.run(DynamicClient.class, args);
	}

	@RequestMapping("/")
	public Data client() {
		return dataClient.data();
	}


	@RequestMapping("/application/{applicationId}")
	public Application application(@PathVariable String applicationId) {
		Application application = eurekaClient.getApplication(applicationId);
		return application;
	}


}
