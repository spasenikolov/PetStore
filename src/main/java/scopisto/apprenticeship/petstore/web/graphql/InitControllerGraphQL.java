package scopisto.apprenticeship.petstore.web.graphql;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.service.PetService;
import scopisto.apprenticeship.petstore.service.UserService;

@Controller
@AllArgsConstructor
public class InitControllerGraphQL {
    private final UserService userService;
    private final PetService petService;

    @MutationMapping
    Iterable<User> createUsers(){
        return this.userService.createUsers();
    }

    @MutationMapping
    Iterable<Pet> createPets(){
        return this.petService.createPets();
    }
}
