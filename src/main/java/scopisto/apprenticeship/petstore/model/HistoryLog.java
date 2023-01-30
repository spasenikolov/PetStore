package scopisto.apprenticeship.petstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class HistoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate dateOfExecution;

    private int numOfSuccessfulPetBuyers;
    private int numOfUsersNotAllowedToBuyPet;

}
