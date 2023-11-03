package com.softroute.shipmentmicroservice.repository;

import com.softroute.shipmentmicroservice.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentEntity, Long> {
    List<ShipmentEntity> getByEmitterId(Long emitterId);

    List<ShipmentEntity> getByReceiverId(Long receiverId);

    ShipmentEntity getByCode(String code);

    List<ShipmentEntity> getByEmitterIdAndReceiverId(Long emitterId, Long receiverId);

    List<ShipmentEntity> getByOriginAgencyId(Long originAgencyId);

    List<ShipmentEntity> getByDestinationAgencyId(Long destinationAgencyId);

}
