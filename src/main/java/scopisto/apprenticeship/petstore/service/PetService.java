package scopisto.apprenticeship.petstore.service;


import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.model.currency.Money;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PetService {
    List<Pet> findAll();
    Optional<Pet> findById(Long id);
    Pet save(String name, String type, String description, LocalDate dateOfBirth, Integer rating);
    Pet save(Pet pet);
    List<Pet> createPets();
    Long generateRandomId();

}
