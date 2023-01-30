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

import scopisto.apprenticeship.petstore.mapper.PetMapper;
import scopisto.apprenticeship.petstore.mapper.UserMapper;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.factory.builder.PetBuilder;
import scopisto.apprenticeship.petstore.service.impl.PetServiceImpl;
import scopisto.apprenticeship.petstore.service.impl.UserServiceImpl;
import scopisto.apprenticeship.petstore.web.dto.PetDTO;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {

    @MockBean
    public PetServiceImpl petService;
    @MockBean
    UserServiceImpl userService;
    @MockBean
    PetMapper petMapper;
    @MockBean
    UserMapper userMapper;
    @Autowired
    MockMvc mockMvc;
    PetBuilder petBuilder;
    Pet dog;

    PetDTO dogDTO;

    @Before
    public void setUp() {
        petBuilder = new PetBuilder();
        dog = petBuilder.name("Test")
                .dateOfBirth(LocalDate.of(2022,1,1))
                .rating(3)
                .description("Test Description")
                .build("DOG");
        dogDTO = PetDTO.builder().name("Test")
                .description("Test Description")
                .dateOfBirth(LocalDate.of(2022,1,1))
                .rating(3)
                .build();

    }

    @Test
    public void listAllPetsTest_success() throws Exception {
        when(this.petService.findAll()).thenReturn(List.of(dog));
        when(this.petMapper.fromEntityToDTO(any())).thenReturn(List.of(dogDTO));

        this.mockMvc.perform(get("/pets")).andExpect(status().isOk()).andReturn();

        verify(petService,times(1)).findAll();
        verify(petMapper, times(1)).fromEntityToDTO(any());
    }
}