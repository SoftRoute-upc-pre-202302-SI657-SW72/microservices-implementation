package upc.edu.pe.softroute.feedbackservice.feignclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upc.edu.pe.softroute.feedbackservice.domain.models.ShipmentEntity;

@FeignClient(name = "shipment-microservice")
public interface ShipmentFeignClient {

    @GetMapping(value = "/api/v1/shipment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ShipmentEntity getShipmentById(@PathVariable("id") Long idShipment);

}
