package com.softroute.enterprisemicroservice.controller;

import com.softroute.enterprisemicroservice.entity.EnterpriseEntity;
import com.softroute.enterprisemicroservice.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enterprise")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnterpriseEntity> saveEnterprise(@Valid @RequestBody EnterpriseEntity enterprise) {
        try {
            EnterpriseEntity enterpriseNew = enterpriseService.save(enterprise);
            return ResponseEntity.status(HttpStatus.CREATED).body(enterpriseNew);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EnterpriseEntity>> getAll() {
        List<EnterpriseEntity> enterprises = enterpriseService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(enterprises);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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


}
