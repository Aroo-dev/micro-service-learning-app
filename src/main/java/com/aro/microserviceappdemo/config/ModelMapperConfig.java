package com.aro.microserviceappdemo.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ModelMapperConfig {







    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}


