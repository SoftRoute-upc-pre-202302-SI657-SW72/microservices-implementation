package com.softroute.enterprisemicroservice.entity;


import javax.persistence.*;

import lombok.*;


@Entity
@Table(name = "enterprise")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name="phone", nullable = false, length = 10)
    private String phone;

    @Column(name="password", nullable = false, length = 50)
    private String password;

    @Column(name="ruc", nullable = false, length = 11)
    private String ruc;

}
