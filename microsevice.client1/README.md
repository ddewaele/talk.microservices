## Introduction

Simple example of a static feign client, accessing a spring boot rest service using a pre-defined url.


Has the following feign dependencies: 
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
    <version>1.1.5.RELEASE</version>
</dependency>
<dependency>
    <groupId>com.netflix.feign</groupId>
    <artifactId>feign-jackson</artifactId>
    <version>8.18.0</version>
</dependency>
```


Contains a simple Feign client

```
package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "Data",url = "${service.data.url}",configuration = FeignJacksonConfiguration.class)
interface DataClient {

		@RequestMapping(value = "/", method = GET)
		Data data();
}
```

And a simple configuration

```
package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class FeignJacksonConfiguration {

    @Inject
    private ObjectMapper objectMapper;

    @Bean
    public Decoder feignDecoder() {return new JacksonDecoder(objectMapper);
    }

}
```