package com.example.licenseservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@ToString
@RedisHash("Organization")
public class Organization extends RepresentationModel<Organization> implements Serializable {
    String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
}
