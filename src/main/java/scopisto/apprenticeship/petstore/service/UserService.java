package scopisto.apprenticeship.petstore.service;

import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.model.currency.Money;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    List<User> buyPetForEachUser();
    User save(String firstName, String lastName, String email, Money budget);
    List<User> createUsers();
    Pet buyPet(Long id, User owner);

}
