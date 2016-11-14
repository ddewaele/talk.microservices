# Eureka Server

## What 

- Service to help you locate other services
- Mid-tier load balancing (not proxy based)
- REST based service / java based client

## Why 

- Services can come and go
- Location transparancy (no hostnames / ip adresses)
- Avoid error prone configs 

## How ?

Add the Eureka server dependency

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka-server</artifactId>
</dependency>
```		

This will allow you to use the `@EnableEurekaServer` annotation.

```
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer.class, args);
	}
}
```

And a small config

```
server:
  port: 8761
eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false
    server:
      waitTimeInMsWhenSyncEmpty: 0
```      

when starting your application, you will 

http://localhost:8761/
http://localhost:8761/eureka/apps
