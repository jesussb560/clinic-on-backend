package com.app.clinicon.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("token")
@Data
public class TokenPropertiesConfig {

	private String header;
	private String prefix;
	private String secret;
	private int expirationInMs;
    
}
