package com.softroute.shipmentmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    private String name;
    private String dni;
    private String cellphoneNumber;
    private String email;
}
