package upc.edu.pe.softroute.feedbackservice.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackUpdateResource {
    private Integer complaintId;
    private String description;
}
