package com.softroute.enterprisemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgencyEntity {
    private Long enterpriseId;
    private String agencyName;
    private String agencyCity;
    private String agencyAddress;
    private String phone;
    private String description;
}
