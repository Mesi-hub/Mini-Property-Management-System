package edu.miu.cs545.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
