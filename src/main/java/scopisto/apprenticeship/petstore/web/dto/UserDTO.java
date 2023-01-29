package scopisto.apprenticeship.petstore.web.dto;

import lombok.*;
import scopisto.apprenticeship.petstore.model.currency.Money;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String emailAddress;

    private Money budget;

    private List<PetDTO> pets;

}
