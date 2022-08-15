package com.app.clinicon.configuration;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ThymeleafTemplateConfig {

    @Bean
    public SpringTemplateEngine springTemplateEngine(){

        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(springResourceTemplateResolver());
        return springTemplateEngine;

    }

    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver(){

        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setPrefix("classpath:/templates/");
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        springResourceTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        springResourceTemplateResolver.setCacheable(false);
        
        return springResourceTemplateResolver;

    }
    
}
