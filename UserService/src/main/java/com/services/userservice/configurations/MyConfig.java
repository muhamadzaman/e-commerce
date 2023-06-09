package com.services.userservice.configurations;

import com.services.cloudinaryservice.services.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig
{
    @Bean
    public ModelMapper modelMapper()
    { return new ModelMapper(); }

    @Bean
    public RestTemplate restTemplate()
    { return new RestTemplate(); }

    @Bean
    public CloudinaryService cloudinaryService()
    { return new CloudinaryService(); }
}