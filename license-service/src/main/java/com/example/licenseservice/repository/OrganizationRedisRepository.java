package com.example.licenseservice.repository;

import com.example.licenseservice.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRedisRepository extends CrudRepository<Organization, String> {

}
