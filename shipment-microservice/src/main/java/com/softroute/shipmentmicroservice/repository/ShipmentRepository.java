package com.softroute.shipmentmicroservice.repository;

import com.softroute.shipmentmicroservice.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity, Long> {
}
