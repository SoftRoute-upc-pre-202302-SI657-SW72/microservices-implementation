package com.softroute.shipmentmicroservice.controller;

import com.softroute.shipmentmicroservice.entity.ShipmentEntity;
import com.softroute.shipmentmicroservice.model.AgencyEntity;
import com.softroute.shipmentmicroservice.model.ClientEntity;
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
    @PostMapping(value="/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShipmentEntity> saveShipment(@Valid @RequestBody ShipmentEntity shipment) {
        try {
            ClientEntity clientEmitter = shipmentService.getClientById(shipment.getEmitterId());
            ClientEntity clientReceiver = shipmentService.getClientById(shipment.getReceiverId());
            AgencyEntity originAgency = shipmentService.getAgencyById(shipment.getOriginAgencyId());
            AgencyEntity destinationAgency = shipmentService.getAgencyById(shipment.getDestinationAgencyId());

            if(clientEmitter == null || clientReceiver == null || originAgency == null || destinationAgency == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(shipmentService.save(shipment));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ShipmentEntity> getAll() {
        return shipmentService.getAll();
    }

    @GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/getByEmitterId/{emitterId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShipmentEntity>> getByEmitterId(@PathVariable("emitterId") Long emitterId) {
        try {
            List<ShipmentEntity> shipments = shipmentService.getByEmitterId(emitterId);
            if (shipments.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shipments);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getByReceiverId/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShipmentEntity>> getByReceiverId(@PathVariable("receiverId") Long receiverId) {
        try {
            List<ShipmentEntity> shipments = shipmentService.getByReceiverId(receiverId);
            if (shipments.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shipments);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getByCode/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShipmentEntity> getByCode(@PathVariable("code") String code) {
        try {
            ShipmentEntity shipment = shipmentService.getByCode(code);
            if (shipment == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shipment);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getByEmitterIdAndReceiverId/{emitterId}/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShipmentEntity>> getByEmitterIdAndReceiverId(@PathVariable("emitterId") Long emitterId, @PathVariable("receiverId") Long receiverId) {
        try {
            List<ShipmentEntity> shipments = shipmentService.getByEmitterIdAndReceiverId(emitterId, receiverId);
            if (shipments == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shipments);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //put by id
    @PutMapping(value = "/updateShipment/{shipmentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShipmentEntity> updateShipment(@PathVariable("shipmentId") Long shipmentId, @RequestBody ShipmentEntity shipmentEntity) {
        try {
            ShipmentEntity shipment = shipmentService.getShipmentById(shipmentId);
            if (shipment == null) {
                return ResponseEntity.notFound().build();
            }
            shipmentEntity.setId(shipment.getId());
            ShipmentEntity shipmentUpdated = shipmentService.update(shipmentEntity);
            if (shipmentUpdated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shipmentUpdated);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //delete by id
    @DeleteMapping(value = "/deleteShipmentById/{shipmentId}")
    public ResponseEntity<ShipmentEntity> deleteShipment(@PathVariable("shipmentId") Long shipmentId) {
        try {
            ShipmentEntity shipment = shipmentService.getShipmentById(shipmentId);
            if (shipment == null) {
                return ResponseEntity.notFound().build();
            }
            shipmentService.deleteById(shipmentId);
            return ResponseEntity.ok(shipment);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/saveClient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> saveClient(@Valid @RequestBody ClientEntity client) {
        try {
            ClientEntity clientNew = shipmentService.saveClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(clientNew);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getByOriginAgencyId/{originAgencyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShipmentEntity>> getByOriginAgencyId(@PathVariable("originAgencyId") Long originAgencyId) {
        try {
            List<ShipmentEntity> shipments = shipmentService.getByOriginAgencyId(originAgencyId);
            if (shipments == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shipments);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getByDestinationAgencyId/{destinationAgencyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShipmentEntity>> getByDestinationAgencyId(@PathVariable("destinationAgencyId") Long destinationAgencyId) {
        try {
            List<ShipmentEntity> shipments = shipmentService.getByDestinationAgencyId(destinationAgencyId);
            if (shipments == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shipments);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getClientByDni/{clientDni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> getClientByDni(@PathVariable("clientDni") String clientDni) {
        try {
            ClientEntity client = shipmentService.getClientByDni(clientDni);
            if (client == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getClientByEmail/{clientEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> getClientByEmail(@PathVariable("clientEmail") String clientEmail) {
        try {
            ClientEntity client = shipmentService.getClientByEmail(clientEmail);
            if (client == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getClientById/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> getClientById(@PathVariable("clientId") Long clientId) {
        try {
            ClientEntity client = shipmentService.getClientById(clientId);
            if (client == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
