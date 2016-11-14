package com.example;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service1 {

    @RequestMapping("/data1")
    public Data getData() {
        return new Data("someData from simple server 1");
    }
}
