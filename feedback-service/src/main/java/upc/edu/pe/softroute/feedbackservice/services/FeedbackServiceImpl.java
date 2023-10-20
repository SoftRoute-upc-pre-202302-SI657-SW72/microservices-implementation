package upc.edu.pe.softroute.feedbackservice.services;

import org.springframework.stereotype.Service;
import upc.edu.pe.softroute.feedbackservice.domain.models.Complaint;
import upc.edu.pe.softroute.feedbackservice.domain.models.Feedback;
import upc.edu.pe.softroute.feedbackservice.domain.repositories.FeedbackRepository;
import upc.edu.pe.softroute.feedbackservice.domain.services.ComplaintService;
import upc.edu.pe.softroute.feedbackservice.domain.services.FeedbackService;
import upc.edu.pe.softroute.feedbackservice.exceptions.NotFoundException;
import upc.edu.pe.softroute.feedbackservice.resources.FeedbackSaveResource;
import upc.edu.pe.softroute.feedbackservice.resources.FeedbackUpdateResource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ComplaintService complaintService;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ComplaintService complaintService) {
        this.feedbackRepository = feedbackRepository;
        this.complaintService = complaintService;
    }

    @Override
    public List<Feedback> getAll() {
        return feedbackRepository.findAllByDeletedDateIsNull();
    }

    @Override
    public List<Feedback> getAllByShipmentId(Integer id) {
        return feedbackRepository.findAllByShipmentIdAndDeletedDateIsNull(id);
    }

    @Override
    public Feedback getById(Integer id) {
        return feedbackRepository.findByIdAndDeletedDateIsNull(id).orElseThrow(() -> new NotFoundException(String.format("Feedback with id %d not found!", id)));
    }

    @Override
    public Feedback save(FeedbackSaveResource saveResource) {

        // TODO: We check if the shipmentId exist

        // We check if the complaint exists
        Complaint complaintSelected = complaintService.getById(saveResource.getComplaintId());

        // Creating the new feedback entity
        Feedback feedback = new Feedback();
        feedback.setDescription(saveResource.getDescription());
        feedback.setCreateDate(LocalDateTime.now());
        feedback.setShipmentId(saveResource.getShipmentId());
        feedback.setComplaint(complaintSelected);

        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback update(Integer id, FeedbackUpdateResource updateResource) {

        // Check if the feedback exits
        Feedback feedback = getById(id);

        // Check if the new complaint exits
        Complaint complaint = complaintService.getById(updateResource.getComplaintId());

        // Updating feedback
        feedback.setDescription(updateResource.getDescription());
        feedback.setComplaint(complaint);

        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback delete(Integer id) {

        // Searching the feedback to delete
        Feedback feedback = getById(id);

        // Do a logical deleting
        feedback.setDeletedDate(LocalDate.now());

        return feedbackRepository.save(feedback);
    }
}
