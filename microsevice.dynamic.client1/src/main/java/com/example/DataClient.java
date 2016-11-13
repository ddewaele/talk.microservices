package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("registered-service2")
interface DataClient {

		@RequestMapping(value = "/data", method = GET)
		Data data();
}