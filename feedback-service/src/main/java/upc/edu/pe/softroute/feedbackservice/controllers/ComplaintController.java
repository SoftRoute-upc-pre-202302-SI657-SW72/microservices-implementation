package upc.edu.pe.softroute.feedbackservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.softroute.feedbackservice.domain.models.Complaint;
import upc.edu.pe.softroute.feedbackservice.domain.services.ComplaintService;
import upc.edu.pe.softroute.feedbackservice.resources.ComplaintResource;
import upc.edu.pe.softroute.feedbackservice.resources.ComplaintSaveResource;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public ResponseEntity<List<ComplaintResource>> getAll() {

        // Get all complaints
        List<Complaint> complaintList = complaintService.getAll();

        // Mapping to complaint DTO
        List<ComplaintResource> complaintResourceList = complaintList.stream().map(complaint ->
                new ComplaintResource(complaint.getId(), complaint.getName())).collect(Collectors.toList());

        return ResponseEntity.ok(complaintResourceList);
    }

    @GetMapping("{id}")
    public ResponseEntity<ComplaintResource> getById(@PathVariable Integer id) {

        // Get the complaint with service
        Complaint complaint = complaintService.getById(id);

        // Mapping to DTO
        ComplaintResource complaintResource = new ComplaintResource(complaint.getId(), complaint.getName());

        return ResponseEntity.ok(complaintResource);
    }

    @PostMapping
    public ResponseEntity<ComplaintResource> save(@RequestBody ComplaintSaveResource saveResource) {

        // Saving using the service
        Complaint complaint = complaintService.save(saveResource);

        // Mapping to DTO
        ComplaintResource complaintResource = new ComplaintResource(complaint.getId(), complaint.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(complaintResource);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ComplaintResource> delete(@PathVariable Integer id) {

        // Deleting complaint
        Complaint complaint = complaintService.delete(id);

        // Mapping to DTO
        ComplaintResource complaintResource = new ComplaintResource(complaint.getId(), complaint.getName());

        return ResponseEntity.ok(complaintResource);

    }
}
