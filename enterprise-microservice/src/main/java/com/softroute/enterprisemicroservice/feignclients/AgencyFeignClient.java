package com.softroute.enterprisemicroservice.feignclients;

import com.softroute.enterprisemicroservice.model.AgencyEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "agency-microservice")
public interface AgencyFeignClient {
    @PostMapping(value = "/api/v1/agency",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    AgencyEntity save(@RequestBody AgencyEntity agency);

    @GetMapping(value = "/api/v1/agency/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    AgencyEntity getById(@PathVariable("id") Long id);

    @GetMapping(value = "/api/v1/agency/searchByNameAgency/{agency_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    AgencyEntity getAgencyByName(@PathVariable("agency_name") String agency_name);

    @GetMapping(value = "/api/v1/agency/searchByAddressAgency/{agency_address}", produces = MediaType.APPLICATION_JSON_VALUE)
    AgencyEntity getAgencyByAddress(@PathVariable("agency_address") String agency_address);

    @GetMapping(value = "/api/v1/agency/searchCityAgencies/{agency_city}", produces = MediaType.APPLICATION_JSON_VALUE)
    AgencyEntity getCityAgencies(@PathVariable("agency_city") String agency_city);

    @GetMapping(value = "/api/v1/agency/searchByEnterpriseId/{enterprise_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    AgencyEntity getAgenciesByEnterpriseId(@PathVariable("enterprise_id") Long enterprise_id);


    @GetMapping(value = "/api/v1/agency/searchByEnterpriseId/{enterprise_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AgencyEntity> findByEnterpriseId(Long enterpriseId);

    @GetMapping(value = "/api/v1/agency/searchByNameAgency/{agency_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AgencyEntity> findAllByAgencyCity(String agencyCity);

}
