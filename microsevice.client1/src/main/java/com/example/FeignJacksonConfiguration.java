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