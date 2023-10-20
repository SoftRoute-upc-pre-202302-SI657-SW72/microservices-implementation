package upc.edu.pe.softroute.feedbackservice.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.softroute.feedbackservice.domain.models.Feedback;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByDeletedDateIsNull();
    List<Feedback> findAllByShipmentIdAndDeletedDateIsNull(Integer id);
    Optional<Feedback> findByIdAndDeletedDateIsNull(Integer id);
}
