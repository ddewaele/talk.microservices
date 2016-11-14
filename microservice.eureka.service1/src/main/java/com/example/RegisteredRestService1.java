package com.example;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisteredRestService1 {

    @RequestMapping("/data1")
    public Data getData() {
        System.out.println("Eureka data service 1 called...");
        return new Data("someData from the registered service 1");
    }

}
