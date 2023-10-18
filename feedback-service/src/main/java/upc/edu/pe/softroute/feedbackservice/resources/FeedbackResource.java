package upc.edu.pe.softroute.feedbackservice.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import upc.edu.pe.softroute.feedbackservice.domain.models.Complaint;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FeedbackResource {
    private Integer id;
    private LocalDateTime createDate;
    private String description;
    private Integer shipmentId;
    private ComplaintResource complaint;
}
