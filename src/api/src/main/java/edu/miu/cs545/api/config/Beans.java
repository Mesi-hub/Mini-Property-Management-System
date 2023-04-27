package edu.miu.cs545.api.config;

import edu.miu.cs545.api.dto.ImageFileInfoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    ModelMapper getModelMapper(ImageFileInfoConverter imageFileInfoConverter) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(imageFileInfoConverter);
        return modelMapper;
    }
}
