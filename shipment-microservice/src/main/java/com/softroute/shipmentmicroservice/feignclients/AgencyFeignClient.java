package com.softroute.shipmentmicroservice.feignclients;

import com.softroute.shipmentmicroservice.model.AgencyEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "agency-microservice")
public interface AgencyFeignClient {
    @GetMapping(value = "/api/v1/agency/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    AgencyEntity getAgencyById(@PathVariable("id") Long idAgency);
}
