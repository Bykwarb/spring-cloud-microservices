package com.example.organizationservice.kafka;

import com.example.organizationservice.events.OrganizationChangeModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class OrgChangeModelSerializer implements Serializer<OrganizationChangeModel> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, OrganizationChangeModel model) {
        try {
            if (model == null){
                return null;
            }
            return objectMapper.writeValueAsBytes(model);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
    }
}
