package com.example.licenseservice;

import com.example.licenseservice.config.ServiceConfig;
import com.example.licenseservice.event.kafka.OrganizationChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.kafka.annotation.KafkaListener;


@SpringBootApplication
@RefreshScope
public class LicenseServiceApplication {
    private final Logger logger = LoggerFactory.getLogger(LicenseServiceApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(LicenseServiceApplication.class, args);
    }
    @Autowired
    private ServiceConfig serviceConfig;

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasenames("messages");
        return messageSource;
    }


}

