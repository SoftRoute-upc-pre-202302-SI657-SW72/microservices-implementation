package com.softroute.enterprisemicroservice.controller;

import com.softroute.enterprisemicroservice.entity.EnterpriseEntity;
import com.softroute.enterprisemicroservice.model.AgencyEntity;
import com.softroute.enterprisemicroservice.service.EnterpriseService;
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
@RequestMapping("/api/v1/enterprise")
@CrossOrigin(origins = "*")
@Api(value = "Enterprise Microservice", description = "This API has a CRUD for Enterprise")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a new Enterprise", notes = "This method save a new Enterprise")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Enterprise created successfully"),
            @ApiResponse(code = 404, message = "Enterprise not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<EnterpriseEntity> saveEnterprise(@Valid @RequestBody EnterpriseEntity enterprise) {
        try {
            EnterpriseEntity existingEnterprise = enterpriseService.getEnterpriseByEmail(enterprise.getRuc());
            if(existingEnterprise != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(existingEnterprise);
            }else{
                EnterpriseEntity enterpriseNew = enterpriseService.save(enterprise);
                return ResponseEntity.status(HttpStatus.CREATED).body(enterpriseNew);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all Enterprises", notes = "This method get all Enterprises")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Enterprises found"),
            @ApiResponse(code = 404, message = "Enterprises not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<EnterpriseEntity>> getAll() {
        List<EnterpriseEntity> enterprises = enterpriseService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(enterprises);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Enterprise by Id", notes = "This method get Enterprise by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Enterprise found"),
            @ApiResponse(code = 404, message = "Enterprise not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<EnterpriseEntity> getEnterpriseById(@PathVariable("id") Long id) {
        try {
            EnterpriseEntity enterprise = enterpriseService.getEnterpriseById(id);
            if (enterprise == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.status(HttpStatus.OK).body(enterprise);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Enterprise by Name", notes = "This method get Enterprise by Name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Enterprise found"),
            @ApiResponse(code = 404, message = "Enterprise not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<EnterpriseEntity> getByNameEnterprise(@PathVariable("name") String name) {
        try {
            EnterpriseEntity enterprise = enterpriseService.getByNameEnterprise(name);
            if (enterprise == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(enterprise, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Enterprise by Email", notes = "This method get Enterprise by Email")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Enterprise found"),
            @ApiResponse(code = 404, message = "Enterprise not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<EnterpriseEntity> getByEmailEnterprise(@PathVariable("email") String email) {
        try {
            EnterpriseEntity enterprise = enterpriseService.getEnterpriseByEmail(email);
            if (enterprise == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(enterprise, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByRuc/{ruc}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Enterprise by Ruc", notes = "This method get Enterprise by Ruc")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Enterprise found"),
            @ApiResponse(code = 404, message = "Enterprise not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<EnterpriseEntity> getEnterpriseByRuc (@PathVariable("ruc") String ruc) {
        try {
            EnterpriseEntity enterprise = enterpriseService.getEnterpriseByRuc(ruc);
            if (enterprise == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(enterprise, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Enterprise", notes = "This method update Enterprise")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Enterprise updated successfully"),
            @ApiResponse(code = 404, message = "Enterprise not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<EnterpriseEntity> updateEnterprise(@PathVariable("id") Long id, @Valid @RequestBody EnterpriseEntity enterprise) {
        try {
            EnterpriseEntity enterpriseUpdated = enterpriseService.getEnterpriseById(id);
            if (enterpriseUpdated == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            enterpriseUpdated.setName(enterprise.getName());
            enterpriseUpdated.setEmail(enterprise.getEmail());
            enterpriseUpdated.setPhone(enterprise.getPhone());
            enterpriseUpdated.setPassword(enterprise.getPassword());
            enterpriseService.save(enterpriseUpdated);
            return ResponseEntity.status(HttpStatus.OK).body(enterpriseUpdated);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Enterprise", notes = "This method delete Enterprise")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Enterprise deleted successfully"),
            @ApiResponse(code = 404, message = "Enterprise not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<EnterpriseEntity> deleteEnterprise(@PathVariable("id") Long id) {
        try {
            EnterpriseEntity enterpriseDeleted = enterpriseService.getEnterpriseById(id);
            if (enterpriseDeleted == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            enterpriseService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(enterpriseDeleted);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/saveAgency", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save a new Agency", notes = "This method save a new Agency")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agency created successfully"),
            @ApiResponse(code = 404, message = "Agency not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<AgencyEntity> saveAgency(@Valid @RequestBody AgencyEntity agency) {
        try {
            AgencyEntity agencyNew = enterpriseService.saveAgency(agency);
            return ResponseEntity.status(HttpStatus.CREATED).body(agencyNew);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/getAgencyById/{agencyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Agency by Id", notes = "This method get Agency by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agency found"),
            @ApiResponse(code = 404, message = "Agency not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<AgencyEntity> getAgencyById(@PathVariable("agencyId") Long agencyId) {
        try {
            AgencyEntity agency = enterpriseService.getAgencyById(agencyId);
            if (agency == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.status(HttpStatus.OK).body(agency);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAgenciesByEnterpriseId/{enterpriseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Agencies by Enterprise Id", notes = "This method get Agencies by Enterprise Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Agencies found"),
            @ApiResponse(code = 404, message = "Agencies not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<AgencyEntity>> getAgenciesByEnterpriseId(@PathVariable("enterpriseId") Long enterpriseId) {
        try {
            List<AgencyEntity> agencies = enterpriseService.getAgenciesByEnterpriseId(enterpriseId);
            if (agencies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.status(HttpStatus.OK).body(agencies);
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
    public ResponseEntity<List<AgencyEntity>> getCityAgencies(@PathVariable("agency_city") String agency_city) {
        try {
            List<AgencyEntity> agencies = enterpriseService.getCityAgencies(agency_city);
            if (agencies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.status(HttpStatus.OK).body(agencies);
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
    public ResponseEntity<AgencyEntity> getAgencyByName(@PathVariable("agency_name") String agency_name) {
        try {
            AgencyEntity agency = enterpriseService.getAgencyByName(agency_name);
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
    public ResponseEntity<AgencyEntity> getAgencyByAddress(@PathVariable("agency_address") String agency_address) {
        try {
            AgencyEntity agency = enterpriseService.getAgencyByAddress(agency_address);
            if (agency == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(agency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
