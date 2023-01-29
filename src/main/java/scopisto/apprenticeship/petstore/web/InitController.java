package scopisto.apprenticeship.petstore.web;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.service.PetService;
import scopisto.apprenticeship.petstore.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/create")
public class InitController {

    private final PetService petService;
    private final UserService userService;

    @PostMapping("/pets")
    public ResponseEntity<List<Pet>> createPets(){
        return new ResponseEntity<>(petService.createPets(), HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<List<User>> createUsers(){
        return new ResponseEntity<>(userService.createUsers(), HttpStatus.OK);
    }

}
