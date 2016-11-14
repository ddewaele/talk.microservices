package com.example;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.net.URI;

@RestController
public class ClientService {

    @Inject
    DataClient dataClient;

    @Inject
    private EurekaClient eurekaClient;

    @RequestMapping("/")
    public Data client() {
        return dataClient.data();
    }

    @RequestMapping("/dependent")
    @HystrixCommand(fallbackMethod = "fallback")
    public Data dependent() {
        URI uri = URI.create("http://localhost:8300/service");
        String dependentData = restTemplate().getForObject(uri, String.class);
        return new Data(dependentData);
    }

    @RequestMapping("/dependent-failure")
    @HystrixCommand(fallbackMethod = "fallback")
    public Data dependentFailure() {
        URI uri = URI.create("http://localhost:8300/service-failure");
        String dependentData = restTemplate().getForObject(uri, String.class);
        return new Data(dependentData);
    }

    public Data fallback(Throwable t) {
        return new Data("no data available due to " + t.getMessage());
    }

    @RequestMapping("/application/{applicationId}")
    public Application application(@PathVariable String applicationId) {
        Application application = eurekaClient.getApplication(applicationId);
        return application;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
