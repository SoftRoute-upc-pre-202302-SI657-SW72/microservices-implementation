package upc.edu.pe.softroute.feedbackservice.domain.services;

import upc.edu.pe.softroute.feedbackservice.domain.models.Feedback;
import upc.edu.pe.softroute.feedbackservice.resources.FeedbackSaveResource;
import upc.edu.pe.softroute.feedbackservice.resources.FeedbackUpdateResource;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAll();
    List<Feedback> getAllByShipmentId(Integer shipmentId);
    Feedback getById(Integer id);
    Feedback save(FeedbackSaveResource saveResource);
    Feedback update(Integer id, FeedbackUpdateResource updateResource);
    Feedback delete(Integer id);
}
