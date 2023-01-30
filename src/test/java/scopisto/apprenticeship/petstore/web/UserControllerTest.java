package scopisto.apprenticeship.petstore.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import scopisto.apprenticeship.petstore.mapper.UserMapper;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.model.currency.Money;
import scopisto.apprenticeship.petstore.model.factory.builder.PetBuilder;
import scopisto.apprenticeship.petstore.service.UserService;
import scopisto.apprenticeship.petstore.web.dto.UserDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    UserService userService;
    @MockBean
    UserMapper userMapper;

    @Autowired
    MockMvc mockMvc;

    User user;
    Pet pet;

    PetBuilder petBuilder;
    UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        petBuilder = new PetBuilder();

        user = User.builder()
                .id(0L)
                .firstName("Test")
                .lastName("Test")
                .budget(new Money(100))
                .emailAddress("test@test.com")
                .pets(new ArrayList<>())
                .build();
        userDTO = UserDTO.builder()
                .firstName("Test")
                .lastName("Test")
                .budget(new Money(10))
                .emailAddress("test@test.com")
                .pets(new ArrayList<>())
                .build();

        pet = petBuilder.name("Test").dateOfBirth(LocalDate.now()).rating(1).build("cat");
    }

    @Test
    void listAllUsers_success() throws Exception {
        when(this.userService.findAll()).thenReturn(List.of(user));
        when(this.userMapper.fromEntityToDTO(any())).thenReturn(List.of(userDTO));

        mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn();

        verify(this.userService,times(1)).findAll();
        verify(this.userMapper,times(1)).fromEntityToDTO(any());
    }

    @Test
    void buyPets_success() throws Exception {
        when(this.userService.buyPetForEachUser()).thenReturn(List.of(user));
        when(this.userMapper.fromEntityToDTO(any())).thenReturn(List.of(userDTO));

        this.mockMvc.perform(post("/users/buy")).andExpect(status().isOk()).andReturn();

        verify(this.userService,times(1)).buyPetForEachUser();
        verify(this.userMapper,times(1)).fromEntityToDTO(any());
    }
}
