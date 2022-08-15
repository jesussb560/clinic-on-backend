package com.app.clinicon.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("mail")
@Data
public class MailPropertiesConfig {

    private String accountActivationUrl;
    
}
