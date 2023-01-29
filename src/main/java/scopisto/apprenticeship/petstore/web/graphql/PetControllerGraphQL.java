package scopisto.apprenticeship.petstore.web.graphql;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.service.PetService;

@Controller
@AllArgsConstructor
public class PetControllerGraphQL {

    private final PetService petService;

    @QueryMapping
    Iterable<Pet> listPets(){
        return this.petService.findAll();
    }
}
