package com.example.licenseservice.event.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrganizationChangeModel {
    private String type;
    private String action;
    private String organizationId;
    private String correlationId;

    @JsonCreator
    public OrganizationChangeModel(@JsonProperty(value = "type") String type,
                                   @JsonProperty(value = "action") String action,
                                   @JsonProperty(value = "organizationId") String organizationId,
                                   @JsonProperty(value = "correlationId") String correlationId) {
        this.type = type;
        this.action = action;
        this.organizationId = organizationId;
        this.correlationId = correlationId;
    }

    public OrganizationChangeModel() {
    }
}
