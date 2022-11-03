package com.example.licenseservice.event.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class OrgChangeModelDeserializer implements Deserializer<OrganizationChangeModel> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public OrganizationChangeModel deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null){
                return null;
            }
            return objectMapper.readValue(bytes, OrganizationChangeModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
