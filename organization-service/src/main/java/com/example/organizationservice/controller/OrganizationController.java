package com.example.organizationservice.controller;

import com.example.organizationservice.model.Organization;
import com.example.organizationservice.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "v1/organization")
public class OrganizationController {
    private Logger logger = LoggerFactory.getLogger(OrganizationController.class);
    @Autowired
    private OrganizationService service;

    @RolesAllowed({"ADMIN", "USER"})
    @RequestMapping(value="/{organizationId}",method = RequestMethod.GET)
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") String organizationId, HttpServletRequest request) {
        return ResponseEntity.ok(service.findById(organizationId));
    }
    @RolesAllowed({"ADMIN", "USER"})
    @RequestMapping(value="/{organizationId}",method = RequestMethod.PUT)
    public void updateOrganization(@PathVariable("organizationId") String id,
                                   @RequestBody Organization organization) {
        service.update(organization);
    }
    @RolesAllowed({"ADMIN", "USER"})
    @PostMapping
    public ResponseEntity<Organization> saveOrganization(@RequestBody Organization organization) {
        return ResponseEntity.ok(service.create(organization));
    }
    @RolesAllowed("ADMIN")
    @RequestMapping(value="/{organizationId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("id") String id,
                                   @RequestBody Organization organization) {
        service.delete(organization);
    }
}
