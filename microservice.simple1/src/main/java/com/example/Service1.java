package com.example;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service1 {

    @RequestMapping("/data")
    public Data getData() {
        return new Data("someData from microservice.simple1");
    }
}
