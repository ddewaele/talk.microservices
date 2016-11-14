package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DependentService1 {

	public static void main(String[] args) {
		SpringApplication.run(DependentService1.class, args);
	}

	@RestController
	class RestService {

		@RequestMapping("/service")
		public String getServiceData() {
			return "data from dependent service...";
		}

		@RequestMapping("/service-failure")
		public String getServiceFailure() throws Exception {
			throw new Exception("Service failure...");
		}

	}
}
