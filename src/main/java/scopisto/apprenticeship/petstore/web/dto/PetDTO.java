package scopisto.apprenticeship.petstore.web.dto;

import lombok.*;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.model.currency.Money;

import java.time.LocalDate;

/**
 * I assume there is no need to return the owner's id,
 * only whether the pet has or does not have an owner.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {

    private boolean hasOwner;

    private String name;

    private String description;

    private LocalDate dateOfBirth;

    private Money price;

    private Integer rating;

}
