package com.softroute.shipmentmicroservice.feignclients;

import com.softroute.shipmentmicroservice.model.ClientEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "client-microservice")
public interface ClientFeignClient {
    @PostMapping(value = "/api/v1/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ClientEntity save(@RequestBody ClientEntity client);
    @GetMapping(value = "/api/v1/client/dni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientEntity getByDni(@PathVariable("dni") String dni);
    @GetMapping(value = "/api/v1/client/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientEntity getByEmail(@PathVariable("email") String email);

    @GetMapping(value = "/api/v1/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientEntity getById(@PathVariable("id") Long id);

}
