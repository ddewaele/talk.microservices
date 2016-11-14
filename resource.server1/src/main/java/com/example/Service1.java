package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service1 {

    @RequestMapping("/resource1")
    public String getResource() {
        return "someData from resource server 1";
    }
}