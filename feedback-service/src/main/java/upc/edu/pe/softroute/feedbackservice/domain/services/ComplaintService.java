package upc.edu.pe.softroute.feedbackservice.domain.services;

import upc.edu.pe.softroute.feedbackservice.domain.models.Complaint;
import upc.edu.pe.softroute.feedbackservice.resources.ComplaintSaveResource;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAll();
    Complaint getById(Integer id);
    Complaint save(ComplaintSaveResource saveResource);
    Complaint delete(Integer id);
}
