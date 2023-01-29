package scopisto.apprenticeship.petstore.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import scopisto.apprenticeship.petstore.mapper.PetMapper;
import scopisto.apprenticeship.petstore.mapper.UserMapper;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.service.PetService;
import scopisto.apprenticeship.petstore.service.UserService;
import scopisto.apprenticeship.petstore.web.dto.PetDTO;
import scopisto.apprenticeship.petstore.web.dto.UserDTO;

import java.util.List;

@Controller
@RequestMapping("/pets")
@AllArgsConstructor
public class PetController {
    private final PetService petService;
    private final UserService userService;
    private final PetMapper petMapper;
    private final UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<List<PetDTO>> listAllPets() {
        return new ResponseEntity<>(petMapper.fromEntityToDTO(this.petService.findAll()),
                HttpStatus.OK);
    }

}
