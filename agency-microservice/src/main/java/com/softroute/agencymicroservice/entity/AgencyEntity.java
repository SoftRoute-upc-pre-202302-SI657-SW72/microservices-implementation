package com.softroute.agencymicroservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "agency")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgencyEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "enterprise_id", nullable = false)
    private Long enterpriseId;

    @Column(name = "agency_name", nullable = false, length = 100)
    private String agencyName;

    @Column(name = "agency_city", nullable = false, length = 100)
    private String agencyCity;

    @Column(name = "agency_address", nullable = false, length = 100)
    private String agencyAddress;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "description", nullable = false, length = 100)
    private String description;
}
