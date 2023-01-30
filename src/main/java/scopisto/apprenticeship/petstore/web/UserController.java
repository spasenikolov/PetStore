package scopisto.apprenticeship.petstore.web;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import scopisto.apprenticeship.petstore.mapper.UserMapper;
import scopisto.apprenticeship.petstore.service.UserService;
import scopisto.apprenticeship.petstore.web.dto.UserDTO;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> listAllUsers(){
        return new ResponseEntity<>(userMapper.fromEntityToDTO(this.userService.findAll()),
                HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity<List<UserDTO>> buyPets() {
        return new ResponseEntity<>(userMapper.fromEntityToDTO(userService.buyPetForEachUser()),
                HttpStatus.OK);

    }

}
