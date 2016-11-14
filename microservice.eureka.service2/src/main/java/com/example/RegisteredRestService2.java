package com.example;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisteredRestService2 {

    @RequestMapping("/data2")
    public Data getData() {
        System.out.println("Eureka data service 2 called...");
        return new Data("someData from the registered service 2");
    }

}
