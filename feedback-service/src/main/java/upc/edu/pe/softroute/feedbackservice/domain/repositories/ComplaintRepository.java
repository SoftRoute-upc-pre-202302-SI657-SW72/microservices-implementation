package upc.edu.pe.softroute.feedbackservice.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.softroute.feedbackservice.domain.models.Complaint;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    List<Complaint> findAllByDeletedDateIsNull();
    Optional<Complaint> findByIdAndDeletedDateIsNull(Integer id);
}
