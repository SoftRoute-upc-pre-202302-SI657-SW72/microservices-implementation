package upc.edu.pe.softroute.feedbackservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "feedbacks")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createDate;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(nullable = false)
    private Integer shipmentId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Complaint complaint;

    @Column(columnDefinition = "DATE")
    private LocalDate deletedDate;
}
