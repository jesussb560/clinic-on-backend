package com.app.clinicon.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("cloudinary")
@Data
public class CloudinaryPropertiesConfig {
    
    private String cloudName;
    private String apiKey;
    private String apiSecret;

}
