package upc.edu.pe.softroute.feedbackservice.resources;

import lombok.Getter;
import lombok.Setter;
import upc.edu.pe.softroute.feedbackservice.domain.models.Complaint;

import java.time.LocalDateTime;

@Getter
@Setter
public class FeedbackSaveResource {
    private Integer shipmentId;
    private Integer complaintId;
    private String description;
}
