package com.example.licenseservice.event;

import com.example.licenseservice.event.kafka.OrganizationChangeModel;
import com.example.licenseservice.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrgEventsHandler {
    private static final Logger logger = LoggerFactory.getLogger(OrgEventsHandler.class);
    @Autowired
    private OrganizationRedisRepository organizationRedisRepository;

    @KafkaListener(topics = "orgChange")
    public void test(OrganizationChangeModel model){
        logger.debug("Received a message of type " + model.getType());
        logger.debug("Received a message with an event {} from the organization service for the organization id {} ", model.getType(), model.getType());
    }


}
