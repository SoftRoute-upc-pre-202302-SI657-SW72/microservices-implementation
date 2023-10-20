package upc.edu.pe.softroute.feedbackservice.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "complaints")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "VARCHAR(60)")
    private String name;

    @Column(columnDefinition = "DATE")
    private LocalDate deletedDate;
}
