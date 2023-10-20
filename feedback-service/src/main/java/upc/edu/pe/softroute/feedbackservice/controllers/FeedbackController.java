package upc.edu.pe.softroute.feedbackservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.softroute.feedbackservice.domain.models.Feedback;
import upc.edu.pe.softroute.feedbackservice.domain.services.FeedbackService;
import upc.edu.pe.softroute.feedbackservice.resources.ComplaintResource;
import upc.edu.pe.softroute.feedbackservice.resources.FeedbackResource;
import upc.edu.pe.softroute.feedbackservice.resources.FeedbackSaveResource;
import upc.edu.pe.softroute.feedbackservice.resources.FeedbackUpdateResource;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResource>> getAll() {

        // Get all feedbacks
        List<Feedback> feedbackList = feedbackService.getAll();

        // Mapping to DTO
        List<FeedbackResource> feedbackResourceList = feedbackList.stream().map(feedback ->
                new FeedbackResource(
                        feedback.getId(),
                        feedback.getCreateDate(),
                        feedback.getDescription(),
                        feedback.getShipmentId(),
                        new ComplaintResource(feedback.getComplaint().getId(), feedback.getComplaint().getName()
                        ))).collect(Collectors.toList());

        return ResponseEntity.ok(feedbackResourceList);
    }

    @GetMapping("{shipmentId}")
    public ResponseEntity<List<FeedbackResource>> getAllByShipmentId(@PathVariable Integer shipmentId) {

        // Get all feedbacks
        List<Feedback> feedbackList = feedbackService.getAllByShipmentId(shipmentId);

        // Mapping to DTO
        List<FeedbackResource> feedbackResourceList = feedbackList.stream().map(feedback ->
                new FeedbackResource(
                        feedback.getId(),
                        feedback.getCreateDate(),
                        feedback.getDescription(),
                        feedback.getShipmentId(),
                        new ComplaintResource(feedback.getComplaint().getId(), feedback.getComplaint().getName()
                        ))).collect(Collectors.toList());

        return ResponseEntity.ok(feedbackResourceList);
    }

    @PostMapping()
    public ResponseEntity<FeedbackResource> save(@RequestBody FeedbackSaveResource saveResource) {

        // Saving using the service
        Feedback feedback = feedbackService.save(saveResource);

        // Mapping to DTO
        FeedbackResource feedbackResource = new FeedbackResource(
                feedback.getId(),
                feedback.getCreateDate(),
                feedback.getDescription(),
                feedback.getShipmentId(),
                new ComplaintResource(feedback.getComplaint().getId(), feedback.getComplaint().getName()));

        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackResource);
    }

    @PutMapping("{id}")
    public ResponseEntity<FeedbackResource> update(@PathVariable Integer id, @RequestBody FeedbackUpdateResource updateResource) {
        // Updating using the service
        Feedback feedback = feedbackService.update(id, updateResource);

        // Mapping to DTO
        FeedbackResource feedbackResource = new FeedbackResource(
                feedback.getId(),
                feedback.getCreateDate(),
                feedback.getDescription(),
                feedback.getShipmentId(),
                new ComplaintResource(feedback.getComplaint().getId(), feedback.getComplaint().getName()));

        return ResponseEntity.ok(feedbackResource);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<FeedbackResource> delete(@PathVariable Integer id) {

        // Deleting using the service
        Feedback feedback = feedbackService.delete(id);

        // Mapping to DTO
        FeedbackResource feedbackResource = new FeedbackResource(
                feedback.getId(),
                feedback.getCreateDate(),
                feedback.getDescription(),
                feedback.getShipmentId(),
                new ComplaintResource(feedback.getComplaint().getId(), feedback.getComplaint().getName()));

        return ResponseEntity.ok(feedbackResource);
    }
}
