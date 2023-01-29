package scopisto.apprenticeship.petstore.mapper;

import org.springframework.stereotype.Component;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.web.dto.PetDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetMapper {

    public List<PetDTO> fromEntityToDTO(List<Pet> pets){
       return pets.stream()
                .map(pet -> PetDTO.builder()
                        .name(pet.getName())
                        .hasOwner(pet.getOwner()!=null)
                        .dateOfBirth(pet.getDateOfBirth())
                        .rating(pet.getRating())
                        .description(pet.getDescription())
                        .price(pet.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
