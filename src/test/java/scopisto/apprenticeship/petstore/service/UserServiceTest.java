package scopisto.apprenticeship.petstore.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.model.currency.Money;
import scopisto.apprenticeship.petstore.model.exceptions.InvalidPetIdException;
import scopisto.apprenticeship.petstore.model.exceptions.PetAlreadyHasAnOwnerException;
import scopisto.apprenticeship.petstore.model.factory.PetCreator;
import scopisto.apprenticeship.petstore.repository.jpa.UserRepository;
import scopisto.apprenticeship.petstore.service.impl.MoneyServiceImpl;
import scopisto.apprenticeship.petstore.service.impl.UserServiceImpl;
import scopisto.apprenticeship.petstore.utils.Generator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PetService petService;
    @Mock
    HistoryLogService historyLogService;
    @Mock
    Generator generator;
    MoneyService moneyService;

    UserServiceImpl userService;

    PetCreator petCreator;
    Pet dog;
    Pet cat;
    User user;

    @Before
    public void setUp() {
        moneyService = new MoneyServiceImpl();
        petCreator = new PetCreator();
        dog = petCreator.createPet("DOG");
        dog.setId(0L);
        dog.setName("TestDog");
        dog.setRating(2);
        dog.setDateOfBirth(LocalDate.of(2022, 1, 1));

        cat = petCreator.createPet("CAT");
        cat.setId(1L);
        cat.setName("TestCat");
        cat.setRating(6);
        cat.setDateOfBirth(LocalDate.of(2010, 1, 1));

        user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("User")
                .emailAddress("test@example.com")
                .budget(new Money(BigDecimal.TEN))
                .pets(new ArrayList<>())
                .build();
        this.userService = new UserServiceImpl(this.userRepository, this.petService, this.historyLogService, this.moneyService);
    }

    @Test
    public void findAllTest_withUser_success() {
        List<User> expectedList = List.of(user);
        when(this.userRepository.findAll()).thenReturn(expectedList);

        List<User> listFromService = this.userService.findAll();

        assertEquals(expectedList, listFromService);
        verify(this.userRepository, times(1)).findAll();

    }

    @Test
    public void findAllTest_noUsers_emptyResult() {
        List<User> expectedList = List.of();
        when(this.userRepository.findAll()).thenReturn(expectedList);

        List<User> listFromService = this.userService.findAll();

        assertEquals(expectedList, listFromService);
        assertEquals(0, listFromService.size());
        verify(this.userRepository, times(1)).findAll();

    }


    @Test
    public void findById_successful() {
        when(this.userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
        User expected = user;

        var result = this.userService.findById(anyLong()).get();

        assertEquals(expected, result);
    }

    @Test
    public void buyPetForEachUser_hasEnoughMoney_successful() {
        when(this.userRepository.findAll()).thenReturn(List.of(user));
        when(this.petService.generateRandomId()).thenReturn(0L);
        when(this.petService.findById(anyLong())).thenReturn(Optional.ofNullable(dog));
        when(this.petService.save(any(Pet.class))).thenReturn(dog);
        doNothing().when(this.historyLogService).insertLog(anyInt(), anyInt());

        var expectedList = List.of(user);
        var result = this.userService.buyPetForEachUser();

        verify(this.petService, times(1)).findById(anyLong());
        assertEquals(expectedList, result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getPets().size());
    }

    @Test
    public void buyPetForEachUser_notEnoughMoney_emptyList() {
        when(this.userRepository.findAll()).thenReturn(List.of(user));
        when(this.petService.generateRandomId()).thenReturn(1L);
        when(this.petService.findById(anyLong())).thenReturn(Optional.ofNullable(cat));
        doNothing().when(this.historyLogService).insertLog(anyInt(), anyInt());

        var result = this.userService.buyPetForEachUser();

        assertEquals(0, result.size());

    }

    @Test
    public void buyPetForEachUser_petWithOwner_EmptyResultList() {
        cat.setOwner(User.builder().build());
        when(this.userRepository.findAll()).thenReturn(List.of(user));
        when(this.petService.generateRandomId()).thenReturn(1L);
        when(this.petService.findById(anyLong())).thenReturn(Optional.ofNullable(cat));
        doNothing().when(this.historyLogService).insertLog(anyInt(), anyInt());

        var result = this.userService.buyPetForEachUser();

        assertEquals(0, result.size());
    }

    @Test
    public void save_success() {
        when(this.userRepository.save(any(User.class))).thenReturn(user);

        this.userService.save(user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getBudget());

        verify(this.userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void createUsers_success() {

        var result = this.userService.createUsers();

        verify(this.userRepository, times(result.size())).save(any(User.class));
    }

    @Test
    public void buyPet_hasNoOwnerAndUserHasBudget_success() {
        when(this.petService.findById(anyLong())).thenReturn(Optional.ofNullable(dog));
        when(this.petService.save(any(Pet.class))).thenReturn(dog);

        var moneyBefore = user.getBudget();
        var result = this.userService.buyPet(dog.getId(), user);

        assertEquals(moneyService.subtract(moneyBefore, dog.getPrice()).getAmount(), user.getBudget().getAmount());
        verify(this.petService, times(1)).save(any(Pet.class));
    }

    @Test
    public void buyPet_hasOwner_PetAlreadyHasAnOwnerException() {
        dog.setOwner(new User());

        when(this.petService.findById(anyLong())).thenReturn(Optional.ofNullable(dog));

        assertThrows(PetAlreadyHasAnOwnerException.class,
                () -> this.userService.buyPet(dog.getId(), user));
    }

    @Test
    public void buyPet_IdDoesNotExists_InvalidPetIdException() {
        assertThrows(InvalidPetIdException.class,
                () -> this.userService.buyPet(dog.getId(), user));
    }

}