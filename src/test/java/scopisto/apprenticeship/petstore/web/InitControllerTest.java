package scopisto.apprenticeship.petstore.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.model.currency.Money;
import scopisto.apprenticeship.petstore.model.factory.builder.PetBuilder;
import scopisto.apprenticeship.petstore.service.PetService;
import scopisto.apprenticeship.petstore.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InitControllerTest {
    @MockBean
    PetService petService;
    @MockBean
    UserService userService;
    @Autowired
    private MockMvc mockMvc;

    PetBuilder petBuilder;

    Pet dog;
    User user;

    @Before
    public void setUp() {
        petBuilder = new PetBuilder();
        dog = petBuilder.name("Test")
                .dateOfBirth(LocalDate.of(2022,1,1))
                .rating(3)
                .description("Test Description")
                .build("DOG");
        user = User.builder()
                .id(0L)
                .firstName("Test")
                .lastName("Test")
                .budget(new Money(100))
                .emailAddress("test@test.com")
                .pets(new ArrayList<>())
                .build();

    }

    @Test
    public void createPets_success() throws Exception {
        when(petService.createPets()).thenReturn(List.of(dog));

        this.mockMvc.perform(post("/create/pets")).andExpect(status().isOk()).andReturn();

        verify(this.petService,times(1)).createPets();
    }

    @Test
    public void createUsers_success() throws Exception {
        when(userService.createUsers()).thenReturn(List.of(user));

        this.mockMvc.perform(post("/create/users")).andExpect(status().isOk()).andReturn();

        verify(this.userService,times(1)).createUsers();
    }
}