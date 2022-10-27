package com.example.licenseservice.security;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloackConfiguration {
    @Bean
    public KeycloakClientRequestFactory keycloakClientRequestFactory(){
        return new KeycloakClientRequestFactory();
    }

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }
}
