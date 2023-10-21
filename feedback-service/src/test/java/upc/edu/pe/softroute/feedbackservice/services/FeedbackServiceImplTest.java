package upc.edu.pe.softroute.feedbackservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import upc.edu.pe.softroute.feedbackservice.domain.models.Feedback;
import upc.edu.pe.softroute.feedbackservice.domain.repositories.ComplaintRepository;
import upc.edu.pe.softroute.feedbackservice.domain.repositories.FeedbackRepository;
import upc.edu.pe.softroute.feedbackservice.domain.services.ComplaintService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FeedbackServiceImplTest {

    @Mock
    FeedbackRepository feedbackRepository;
    ComplaintRepository complaintRepository;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;
    private ComplaintService complaintService;
    private Feedback feedback;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        feedback = new Feedback();
        feedback.setId(11);
        feedback.setShipmentId(11);
        feedback.setDescription("Feedback description");
    }

    @Test
    void getAll() {
        when(feedbackRepository.findAllByDeletedDateIsNull()).thenReturn(Arrays.asList(feedback));
        assertNotNull(feedbackService.getAll());
    }

    @Test
    void getAllByShipmentId() {
        when(feedbackRepository.findAllByShipmentIdAndDeletedDateIsNull(11)).thenReturn(Arrays.asList(feedback));
        assertNotNull(feedbackService.getAllByShipmentId(11));
    }

    @Test
    void getById() {
        when(feedbackRepository.findByIdAndDeletedDateIsNull(11)).thenReturn(java.util.Optional.of(feedback));
        assertNotNull(feedbackService.getById(11));
    }

}