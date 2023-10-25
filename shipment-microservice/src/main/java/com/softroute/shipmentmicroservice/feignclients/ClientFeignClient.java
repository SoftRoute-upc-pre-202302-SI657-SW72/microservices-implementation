package com.softroute.shipmentmicroservice.feignclients;

import com.softroute.shipmentmicroservice.model.ClientEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "client-microservice", url = "http://localhost:8081/api/v1/client")
public interface ClientFeignClient {
    @PostMapping
    ClientEntity save(@RequestBody ClientEntity client);
}
