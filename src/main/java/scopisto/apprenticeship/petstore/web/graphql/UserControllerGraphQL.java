package scopisto.apprenticeship.petstore.web.graphql;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.repository.jpa.UserRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import scopisto.apprenticeship.petstore.service.PetService;
import scopisto.apprenticeship.petstore.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserControllerGraphQL {

    private final UserService userService;

    @QueryMapping
    public Iterable<User> listUsers(){
        return this.userService.findAll();
    }

    @MutationMapping
    public Iterable<User> buy(){
        return this.userService.buyPetForEachUser();
    }

}
