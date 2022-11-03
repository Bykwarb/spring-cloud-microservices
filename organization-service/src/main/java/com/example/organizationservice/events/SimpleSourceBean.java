package com.example.organizationservice.events;

import com.example.organizationservice.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;



@Component
public class SimpleSourceBean {
    @Autowired
    private KafkaTemplate<String, OrganizationChangeModel> kafkaTemplate;
    private String topic = "orgChange";
    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    public void publishOrganizationChange(String action, String organizationId){
        logger.debug("Sending Kafka message {} for Organization Id: {}", action, organizationId);
        OrganizationChangeModel change = new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                organizationId,
                UserContext.getCorrelationId()
        );
        kafkaTemplate.send(topic, change);
    }

}
