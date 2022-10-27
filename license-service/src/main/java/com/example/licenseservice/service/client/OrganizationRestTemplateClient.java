package com.example.licenseservice.service.client;

import com.example.licenseservice.model.Organization;
import com.example.licenseservice.repository.OrganizationRedisRepository;
import com.example.licenseservice.utils.UserContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);
    @Autowired
    KeycloakRestTemplate keycloakRestTemplate;

    @Autowired
    OrganizationRedisRepository redisRepository;
    public Organization getOrganization(String organizationId){
        logger.error("In Licensing Service.getOrganization: {}",UserContext.getOrganizationId());
        Organization organization = checkRedisCache(organizationId);
        if (organization != null){
            logger.debug("I have succesfully retrieved an organization {} from the redis cache: {}",
                    organizationId, organization);
            return organization;
        }
        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);
        ResponseEntity<Organization> restExchange = keycloakRestTemplate.exchange(
                "http://localhost:8072/organization/v1/organization/{organizationId}",
                HttpMethod.GET,
                null, Organization.class, organizationId);
        organization = restExchange.getBody();
        if (organization != null){
            cacheOrganizationObject(organization);
        }
        return restExchange.getBody();
    }

    private Organization checkRedisCache(String organizationId){
        try {
            return redisRepository.findById(organizationId).orElse(null);
        }catch (Exception e){
            logger.error("Error encountered while trying to retrieve " +
                         "organization{} check Redis Cache. Exception {}",
                    organizationId, e);
            return null;
        }
    }

    private void cacheOrganizationObject(Organization organization){
        try {
            redisRepository.save(organization);
        }catch (Exception e){
            logger.error("Unable to cache organization {} in Redis." +
                    "Exception {}", organization.getId(), e);
        }
    }


}
