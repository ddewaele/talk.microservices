package com.example;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service2 {

    @RequestMapping("/data2")
    public Data getData() {
        return new Data("someData from simple server 2");
    }
}
