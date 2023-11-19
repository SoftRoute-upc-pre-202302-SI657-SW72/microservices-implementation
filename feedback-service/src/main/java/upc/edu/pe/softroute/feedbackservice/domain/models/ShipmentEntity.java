package upc.edu.pe.softroute.feedbackservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentEntity {

    private Long Id;
    private String description;
    private int quantity;
    private String dateRegistered;
    private String code;
    private Boolean deliveredStatus;
    private Long originAgencyId;
    private Long destinationAgencyId;
    private String typePackage;
    private Long emitterId;
    private Long receiverId;
    private String locationInformation;

}
