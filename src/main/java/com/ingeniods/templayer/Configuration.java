package com.ingeniods.templayer;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public TemplateEngine templateEngine(){
        return new TemplateApplyer();
    }
}
