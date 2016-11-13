package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "Data",url = "${service.data.url}")
interface DataClient {

		@RequestMapping(value = "/data", method = GET)
		Data data();
}