package com.softroute.shipmentmicroservice.service;

import com.softroute.shipmentmicroservice.entity.ShipmentEntity;
import com.softroute.shipmentmicroservice.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    public List<ShipmentEntity> getAll() {
        return shipmentRepository.findAll();
    }

    public ShipmentEntity getShipmentById(Long id) {
        return shipmentRepository.findById(id).orElse(null);
    }

    public ShipmentEntity save(ShipmentEntity shipment) {
        return shipmentRepository.save(shipment);
    }

    public void delete(Long id) {
        shipmentRepository.deleteById(id);
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

    public ShipmentEntity getByEmitterIdAndReceiverId(Long emitterId, Long receiverId) {
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


}
