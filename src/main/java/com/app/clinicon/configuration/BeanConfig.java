package com.app.clinicon.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final CloudinaryPropertiesConfig cloudinaryPropertiesConfig;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudinaryPropertiesConfig.getCloudName(),
            "api_key", cloudinaryPropertiesConfig.getApiKey(),
            "api_secret", cloudinaryPropertiesConfig.getApiSecret(),
            "secure", true
        ));
    }
    
}
