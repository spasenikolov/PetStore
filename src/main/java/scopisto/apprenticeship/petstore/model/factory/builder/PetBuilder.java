package scopisto.apprenticeship.petstore.model.factory.builder;


import org.springframework.stereotype.Component;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.factory.PetCreator;

import java.time.LocalDate;
@Component
public class PetBuilder {
    private String name;
    private String description;
    private LocalDate dateOfBirth;
    private Integer rating;

    public PetBuilder setName(String name){
        this.name = name;
        return this;
    }
    public PetBuilder setDescription(String description){
        this.description = description;
        return this;
    }
    public PetBuilder setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public PetBuilder setRating(Integer rating){
        this.rating = rating;
        return this;
    }
    public Pet build(String type){
        PetCreator petCreator = new PetCreator();
        return petCreator.createPet(name,type,description,dateOfBirth,rating);
    }



}
