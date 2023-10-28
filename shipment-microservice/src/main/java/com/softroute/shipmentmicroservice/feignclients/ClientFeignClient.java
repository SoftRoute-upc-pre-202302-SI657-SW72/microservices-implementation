package com.softroute.shipmentmicroservice.feignclients;

import com.softroute.shipmentmicroservice.model.ClientEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "client-microservice", url = "http://localhost:8081/api/v1/client")
public interface ClientFeignClient {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ClientEntity save(@RequestBody ClientEntity client);
    @GetMapping(value = "/dni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientEntity getByDni(@PathVariable("dni") String dni);
    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientEntity getByEmail(@PathVariable("email") String email);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientEntity getById(@PathVariable("id") Long id);

}
