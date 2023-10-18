package com.softroute.shipmentmicroservice.controller;

import com.softroute.shipmentmicroservice.entity.ShipmentEntity;
import com.softroute.shipmentmicroservice.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shipment")

public class ShipmentController {
    @Autowired
    ShipmentService shipmentService;

    //Mejorar, pedi el id del emitter, receiver, agency emitter and reciever
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShipmentEntity> saveShipment(@Valid @RequestBody ShipmentEntity shipment) {
        try {
            ShipmentEntity shipmentNew = shipmentService.save(shipment);
            return ResponseEntity.status(HttpStatus.CREATED).body(shipmentNew);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ShipmentEntity> getAll() {
        return shipmentService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShipmentEntity> getShipmentById(@PathVariable("id") Long id) {
        try {
            ShipmentEntity shipment = shipmentService.getShipmentById(id);
            if (shipment == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shipment);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
