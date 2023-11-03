package com.softroute.shipmentmicroservice.service;

import com.softroute.shipmentmicroservice.entity.ShipmentEntity;
import com.softroute.shipmentmicroservice.feignclients.AgencyFeignClient;
import com.softroute.shipmentmicroservice.feignclients.ClientFeignClient;
import com.softroute.shipmentmicroservice.model.AgencyEntity;
import com.softroute.shipmentmicroservice.repository.ShipmentRepository;
import com.softroute.shipmentmicroservice.model.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    ClientFeignClient clientFeignClient;

    @Autowired
    AgencyFeignClient agencyFeignClient;

    public List<ShipmentEntity> getAll() {
        return shipmentRepository.findAll();
    }

    public ShipmentEntity getShipmentById(Long id) {
        return shipmentRepository.findById(id).orElse(null);
    }

    public ShipmentEntity save(ShipmentEntity shipment) {
        return shipmentRepository.save(shipment);
    }

    public List<ShipmentEntity> getByEmitterId(Long emitterId) {
        return shipmentRepository.getByEmitterId(emitterId);
    }

    public List<ShipmentEntity> getByReceiverId(Long receiverId) {
        return shipmentRepository.getByReceiverId(receiverId);
    }

    public ShipmentEntity getByCode(String code) {
        return shipmentRepository.getByCode(code);
    }

    public List<ShipmentEntity> getByEmitterIdAndReceiverId(Long emitterId, Long receiverId) {
        return shipmentRepository.getByEmitterIdAndReceiverId(emitterId, receiverId);
    }

    //put
    public ShipmentEntity update(ShipmentEntity shipmentEntity) {
        return shipmentRepository.save(shipmentEntity);
    }

    //delete by id
    public void deleteById(Long id) {
        shipmentRepository.deleteById(id);
    }

    public ClientEntity saveClient(ClientEntity client) {
        return clientFeignClient.save(client);
    }

    public List<ShipmentEntity> getByOriginAgencyId(Long originAgencyId) {
        return shipmentRepository.getByOriginAgencyId(originAgencyId);
    }

    public List<ShipmentEntity> getByDestinationAgencyId(Long destinationId) {
        return shipmentRepository.getByDestinationAgencyId(destinationId);
    }

    public ClientEntity getClientByDni(String clientDni) {
        return clientFeignClient.getByDni(clientDni);
    }

    public ClientEntity getClientByEmail(String clientEmail) {
        return clientFeignClient.getByEmail(clientEmail);
    }

    public ClientEntity getClientById(Long clientId) {
        return clientFeignClient.getById(clientId);
    }

    public AgencyEntity getAgencyById(Long agencyId) {
        return agencyFeignClient.getAgencyById(agencyId);
    }


}
