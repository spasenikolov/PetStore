package scopisto.apprenticeship.petstore.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.factory.builder.PetBuilder;
import scopisto.apprenticeship.petstore.repository.jpa.PetRepository;
import scopisto.apprenticeship.petstore.service.impl.PetServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PetServiceTest {

    @Mock
    PetRepository petRepository;
    PetBuilder petBuilder;
    PetServiceImpl petService;

    Pet dog;
    Pet cat;

    @Before
    public void setUp() {
        petBuilder = new PetBuilder();
        dog = petBuilder.name("Test")
                .dateOfBirth(LocalDate.of(2022, 2, 1))
                .description("Test Description")
                .rating(2)
                .id(0L)
                .build("DOG");

        cat = petBuilder.name("Test")
                .dateOfBirth(LocalDate.of(2022, 4, 1))
                .description("Test Description")
                .rating(2)
                .id(1L)
                .build("CAT");

        this.petService = new PetServiceImpl(this.petRepository, this.petBuilder);
    }

    @Test
    public void findAll_withPets_success() {
        List<Pet> listOfPets = List.of(cat, dog);
        when(this.petRepository.findAll()).thenReturn(listOfPets);

        var result = this.petService.findAll();

        assertEquals(2, result.size());
        assertEquals(listOfPets, result);
        verify(this.petRepository, times(1)).findAll();
    }

    @Test
    public void findAll_withoutPets_emptyResult() {
        List<Pet> listOfPets = List.of();
        when(this.petRepository.findAll()).thenReturn(listOfPets);

        var result = this.petService.findAll();

        assertEquals(0, result.size());
        assertEquals(listOfPets, result);
        verify(this.petRepository, times(1)).findAll();
    }

    @Test
    public void findById_withValidId_success() {
        when(this.petRepository.findById(anyLong())).thenReturn(Optional.ofNullable(dog));

        var result = this.petService.findById(6L).get();

        verify(this.petRepository, times(1)).findById(anyLong());
        assertEquals(dog, result);
    }

    @Test
    public void findById_withInvalidId_throwsNoSuchElementException() {
        when(this.petRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        assertThrows(NoSuchElementException.class,
                () -> this.petService.findById(6L).get());

    }

    @Test
    public void save_withArguments_success() {
        when(this.petRepository.save(any(Pet.class))).thenReturn(dog);

        var result = this.petService.save(dog.getName(), dog.getType(), dog.getDescription(), dog.getDateOfBirth(), dog.getRating());

        assertEquals(dog, result);
        verify(this.petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    public void save_withArgumentsAndIncorrectType_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> this.petService.save(dog.getName(), "Rabbit", dog.getDescription(), dog.getDateOfBirth(), dog.getRating()));
    }

    @Test
    public void save_success() {
        when(this.petRepository.save(any(Pet.class))).thenReturn(dog);

        var result = this.petService.save(dog);

        assertEquals(dog, result);
        verify(this.petRepository, times(1)).save(any(Pet.class));
    }

    @Test
    public void createPets_success() {
        var result = this.petService.createPets();

        verify(this.petRepository, times(result.size())).save(any(Pet.class));
    }

    @Test
    public void generateRandomId_success() {
        List<Pet> listDog = new ArrayList<>(List.of(dog));
        when(this.petRepository.findAll()).thenReturn(listDog);

        var result = this.petService.generateRandomId();

        assertEquals(Optional.of(0L), Optional.ofNullable(result));
    }
}