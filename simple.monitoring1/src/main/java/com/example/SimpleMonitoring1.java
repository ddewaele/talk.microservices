package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SimpleMonitoring1 {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMonitoring1.class, args);
	}

	@RestController
	class RestService {

		@RequestMapping("/test/{number}")
		public void test(@PathVariable long number) throws InterruptedException {
			if (number==666) throw new IllegalArgumentException("DEVIL !!!!!!!");
			Thread.sleep(number);
		}
	}
}
