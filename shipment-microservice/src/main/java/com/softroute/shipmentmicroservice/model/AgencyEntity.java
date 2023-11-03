package com.softroute.shipmentmicroservice.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgencyEntity {
    private Long id;
    private Long enterpriseId;
    private String agencyName;
    private String agencyCity;
    private String agencyAddress;
    private String phone;
    private String description;
}