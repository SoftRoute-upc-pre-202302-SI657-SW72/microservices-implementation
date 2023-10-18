package com.softroute.shipmentmicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shipment")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ShipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "date_registered", nullable = false)
    private Date dateRegistered;
    @Column(name = "code", nullable = false, length = 10, unique = true, updatable = false, columnDefinition = "varchar(10) default '0000000000'")
    private String code;
    @Column(name = "delivered_status", nullable = false, columnDefinition = "boolean default false")
    private Boolean deliveredStatus;
    @Column(name = "origin_agency_id", nullable = false)
    private Long originAgencyId;
    @Column(name = "destination_agency_id", nullable = false)
    private Long destinationAgencyId;
    @Column(name = "type_package_id", nullable = false)
    private Long typePackageId;
    @Column(name = "emitter_id", nullable = false)
    private Long emitterId;
    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;
    @Column(name = "location_information_id", nullable = false)
    private Long locationInformationId;

}
