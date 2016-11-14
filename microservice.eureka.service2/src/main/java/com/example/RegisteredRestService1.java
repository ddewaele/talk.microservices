package com.example;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisteredRestService1 {

    @RequestMapping("/data")
    public Data getData() {
        System.out.println("Eureka data service called...");
        return new Data("someData from the registered service");
    }

}
