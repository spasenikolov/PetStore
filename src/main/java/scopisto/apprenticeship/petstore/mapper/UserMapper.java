package scopisto.apprenticeship.petstore.mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.web.dto.UserDTO;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private final PetMapper petMapper;

    public List<UserDTO> fromEntityToDTO (List<User> users){
        return users.stream()
                .map(user ->
                    UserDTO.builder()
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .budget(user.getBudget())
                            .emailAddress(user.getEmailAddress())
                            .pets(petMapper.fromEntityToDTO(user.getPets()))
                            .build()
                )
                .collect(Collectors.toList());
    }
}
