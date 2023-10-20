package upc.edu.pe.softroute.feedbackservice.services;

import org.springframework.stereotype.Service;
import upc.edu.pe.softroute.feedbackservice.domain.models.Complaint;
import upc.edu.pe.softroute.feedbackservice.domain.repositories.ComplaintRepository;
import upc.edu.pe.softroute.feedbackservice.domain.services.ComplaintService;
import upc.edu.pe.softroute.feedbackservice.exceptions.NotFoundException;
import upc.edu.pe.softroute.feedbackservice.resources.ComplaintSaveResource;

import java.time.LocalDate;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public List<Complaint> getAll() {
        return complaintRepository.findAllByDeletedDateIsNull();
    }

    @Override
    public Complaint getById(Integer id) {
        return complaintRepository.findByIdAndDeletedDateIsNull(id).orElseThrow(() -> new NotFoundException(String.format("Complaint with id %d not found!", id)));
    }

    @Override
    public Complaint save(ComplaintSaveResource saveResource) {

        // Creating the new complaint entity
        Complaint complaint = new Complaint();

        // Updating the data
        complaint.setName(saveResource.getName());

        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint delete(Integer id) {

        // Searching the complaint
        Complaint complaint = getById(id);

        // We do a logical elimination
        complaint.setDeletedDate(LocalDate.now());

        return complaintRepository.save(complaint);
    }
}
