package com.softroute.agencymicroservice.controller;

import com.softroute.agencymicroservice.entity.AgencyEntity;
import com.softroute.agencymicroservice.service.AgencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/agency")
@CrossOrigin(origins = "*")
@Api(value = "Agency Microservice", description = "This API has a CRUD for Agency")
public class AgencyController {

    @Autowired
    AgencyService agencyService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a new Agency", notes = "This method save a new Agency")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agency created successfully"),
            @ApiResponse(code = 404, message = "Agency not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<AgencyEntity> saveAgency(@Valid @RequestBody AgencyEntity agency) {
        try {
            AgencyEntity agencyNew = agencyService.save(agency);
            return ResponseEntity.status(HttpStatus.CREATED).body(agencyNew);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Agencies", notes = "This method get all Agencies")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agencies found"),
            @ApiResponse(code = 404, message = "Agencies not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<AgencyEntity>> getAll() {
        List<AgencyEntity> agencies = agencyService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(agencies);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Agency by Id", notes = "This method get Agency by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agency found"),
            @ApiResponse(code = 404, message = "Agency not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<AgencyEntity> getAgencyById(@PathVariable("id") Long idAgency) {
        try {
            AgencyEntity agency = agencyService.getAgencyById(idAgency);
            if (agency == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.status(HttpStatus.OK).body(agency);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByNameAgency/{agency_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Agency by Name", notes = "This method get Agency by Name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agency found"),
            @ApiResponse(code = 404, message = "Agency not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<AgencyEntity> getAgencyByName(@PathVariable("agency_name") String agencyName) {
        try {
            AgencyEntity agency = agencyService.getAgencyByName(agencyName);
            if (agency == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(agency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByAddressAgency/{agency_address}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Agency by Address", notes = "This method get Agency by Address")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agency found"),
            @ApiResponse(code = 404, message = "Agency not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<AgencyEntity> getAgencyByAddress(@PathVariable("agency_address") String agencyAddress) {
        try {
            AgencyEntity agency = agencyService.getAgencyByAddress(agencyAddress);
            if (agency == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(agency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchCityAgencies/{agency_city}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Agencies by City", notes = "This method get Agencies by City")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agencies found"),
            @ApiResponse(code = 404, message = "Agencies not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public List<AgencyEntity> getCityAgencies(@PathVariable("agency_city") String agencyCity) {
        return agencyService.getCityAgencies(agencyCity);
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Agency by Id", notes = "This method delete Agency by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agency deleted successfully"),
            @ApiResponse(code = 404, message = "Agency not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<AgencyEntity> deleteAgency(@PathVariable("id") Long idAgency) {
        try {
            AgencyEntity agency = agencyService.getAgencyById(idAgency);
            if (agency == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            agencyService.delete(idAgency);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Agency by Id", notes = "This method update Agency by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agency updated successfully"),
            @ApiResponse(code = 404, message = "Agency not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<AgencyEntity> updateAgency(@PathVariable("id") Long idAgency, @Valid @RequestBody AgencyEntity agency) {
        try {
            AgencyEntity agencyOld = agencyService.getAgencyById(idAgency);
            if (agencyOld == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            agency.setId(idAgency);
            agencyService.save(agency);
            return new ResponseEntity<>(agency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }








}
