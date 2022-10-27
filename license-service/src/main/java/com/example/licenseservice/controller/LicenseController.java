package com.example.licenseservice.controller;

import com.example.licenseservice.model.License;
import com.example.licenseservice.service.LicenseService;
import com.example.licenseservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
public class LicenseController {
@Autowired
private LicenseService licenseService;
    private static final Logger logger = LoggerFactory.getLogger(LicenseService.class);

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public List<License> getLicense( @PathVariable("organizationId") String organizationId) {
        logger.debug("LicenseServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        return licenseService.getLicensesByOrganization(organizationId);
    }


    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organizationId") String organizationId,
                                              @PathVariable("licenseId") String licenseId){
        License license = licenseService.getLicense(licenseId, organizationId);
        license.add(
                linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId())).withSelfRel(),
                linkTo(methodOn(LicenseController.class).createLicense(license)).withRel("createLicense"),
                linkTo(methodOn(LicenseController.class).updateLicense(license)).withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class).deleteLicense(license.getLicenseId())).withRel("deleteLicense"));
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(@RequestBody License request) {
        return ResponseEntity.ok(licenseService.updateLicense(request));
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@RequestBody License request){
        return ResponseEntity.ok(licenseService.createLicense(request));
    }

    @DeleteMapping(value="/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }

    @GetMapping(value = "/{licenseId}/{clientType}")
    public License getLicenseWithClient(@PathVariable("organizationId") String organizationId,
                                        @PathVariable("licenseId") String licenseId){
        return licenseService.getLicense(organizationId, licenseId);
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicensesByOrganization(organizationId);
    }
}
