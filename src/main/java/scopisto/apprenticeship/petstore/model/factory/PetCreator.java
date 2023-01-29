package scopisto.apprenticeship.petstore.model.factory;

import org.springframework.stereotype.Component;
import scopisto.apprenticeship.petstore.model.Cat;
import scopisto.apprenticeship.petstore.model.Dog;
import scopisto.apprenticeship.petstore.model.Pet;

import java.time.LocalDate;
import java.util.Locale;

@Component
public class PetCreator {

    public PetCreator() {
    }

    public Pet createPet(String type){
        String pet = type.toUpperCase(Locale.ROOT);

        return switch (pet) {
            case "DOG" -> new Dog();
            case "CAT" -> new Cat();
            default -> throw new IllegalArgumentException(pet);
        };
    }
    public Pet createPet(String name, String type, String description, LocalDate date, Integer rating) {
        Pet pet = createPet(type);
        pet.setName(name);
        pet.setDescription(description);
        pet.setRating(rating);
        pet.setDateOfBirth(date);
        pet.setPrice(pet.getPrice());
        pet.setType(type.toUpperCase(Locale.ROOT));
        return pet;
    }
}
