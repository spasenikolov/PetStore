package scopisto.apprenticeship.petstore.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import scopisto.apprenticeship.petstore.dataholder.Names;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.currency.Money;
import scopisto.apprenticeship.petstore.model.factory.builder.PetBuilder;
import scopisto.apprenticeship.petstore.repository.jpa.PetRepository;
import scopisto.apprenticeship.petstore.service.PetService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetBuilder petBuilder;

    @Override
    public List<Pet> findAll() {
        return this.petRepository.findAll();
    }
    @Override
    public Optional<Pet> findById(Long id) {
        return this.petRepository.findById(id);
    }

    @Override
    public Pet save(String name, String type, String description, LocalDate dateOfBirth, Integer rating) {
        Pet pet = petBuilder.name(name)
                .description(description)
                .dateOfBirth(dateOfBirth)
                .rating(rating)
                .build(type);
        return this.petRepository.save(pet);
    }
    @Override
    public Pet save(Pet pet) {
        return this.petRepository.save(pet);
    }

    @Override
    public List<Pet> createPets() {

        Random random = new Random();
        List<Pet> resultList = new ArrayList<>();
        List<String> petNames = Names.petNames;
        List<String> descriptionNames = Names.descriptions;
        List<String> types = Names.petTypes;

        IntStream.range(0, random.nextInt(20)).forEach(i -> {

            String name = petNames.get(random.nextInt(petNames.size()));
            String description = descriptionNames.get(random.nextInt(descriptionNames.size()));
            LocalDate dateOfBirth = LocalDate.now().minusDays((long) (Math.random() * 1000));
            Money price = new Money(random.nextInt(300));
            Integer rating = random.nextInt(11);
            String type = types.get(random.nextInt(2));

            Pet pet = petBuilder.name(name)
                    .description(description)
                    .dateOfBirth(dateOfBirth)
                    .rating(rating)
                    .build(type);

            this.petRepository.save(pet);
            resultList.add(pet);
        });
        return resultList;
    }

    @Override
    public Long generateRandomId() {
        List<Pet> allPets = this.petRepository.findAll();
        int randomIndex = (int) Math.floor(Math.random() * allPets.size());
        Pet pet = this.petRepository.findAll().get(randomIndex);
        return pet.getId();
    }
}
