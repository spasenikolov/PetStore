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
    private Long id;

    public PetBuilder name(String name){
        this.name = name;
        return this;
    }
    public PetBuilder description(String description){
        this.description = description;
        return this;
    }
    public PetBuilder dateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    public PetBuilder rating(Integer rating){
        this.rating = rating;
        return this;
    }
    public PetBuilder id(Long id){
        this.id = id;
        return this;
    }
    public Pet build(String type){
        PetCreator petCreator = new PetCreator();
        return petCreator.createPet(name,type,description,dateOfBirth,rating);
    }




}
