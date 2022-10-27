package com.example.licenseservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter

public class ServiceConfig {
    @Value("${example.property}")
    private String property;
}
