package scopisto.apprenticeship.petstore.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import scopisto.apprenticeship.petstore.dataholder.Names;
import scopisto.apprenticeship.petstore.model.Pet;
import scopisto.apprenticeship.petstore.model.User;
import scopisto.apprenticeship.petstore.model.currency.Money;
import scopisto.apprenticeship.petstore.model.exceptions.InsufficientAmountException;
import scopisto.apprenticeship.petstore.model.exceptions.InvalidPetIdException;
import scopisto.apprenticeship.petstore.model.exceptions.PetAlreadyHasAnOwnerException;
import scopisto.apprenticeship.petstore.repository.jpa.UserRepository;
import scopisto.apprenticeship.petstore.service.HistoryLogService;
import scopisto.apprenticeship.petstore.service.PetService;
import scopisto.apprenticeship.petstore.service.UserService;
import scopisto.apprenticeship.petstore.utils.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
/**
 * The create-users and create-pets methods can be implemented as a init component that runs on start,
 * but i assume the idea is that the methods can be used multiple times thus i implemented them as service layer methods.
 *
 * For the buy requriments, I assume that every user needs to try to buy a random pet.
 * Other option: try to buy pet the first pet that you have money for (iterate the pets in the database).
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PetService petService;
    private final HistoryLogService historyLogService;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    /**
     * I am not sure about the semantics of the max value
     * in the requirements for create-users and create-pets
     * (whether it's a constraint on the table or max created users in one run).
     * Since it makes no sense to me to limit the table to 10 or 20 users
     * I assume it's the latter.
     * So, I generate a random number of objects with a limit of 10 or 20
     * depending on whether it's a user or a pet.*/
    @Override
    public List<User> buyPetForEachUser() {
        List<User> allUsers = this.userRepository.findAll();
        List<User> successUsers = new ArrayList<>();
        allUsers.forEach(user -> {
                    try {
                        Pet pet = this.buyPet(this.petService.generateRandomId(), user);
                        successUsers.add(user);
                        System.out.println(pet.printBuyMessage());
                    } catch (PetAlreadyHasAnOwnerException | InsufficientAmountException e) {
                        System.out.println(e.getMessage());
                    }
                });
        this.historyLogService.insertLog(successUsers.size(), allUsers.size() - successUsers.size());
        return successUsers;
    }

    @Override
    public User save(String firstName, String lastName, String email, Money budget) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .emailAddress(email)
                .budget(budget)
                .build();

        return this.userRepository.save(user);
    }

    @Override
    public List<User> createUsers() {
        Generator generator = new Generator();
        Random random = new Random();
        List<User> resultList = new ArrayList<>();
        List<String> userFirstNames = Names.userFirstNames;
        List<String> userLastNames = Names.userLastNames;

        IntStream.range(0, random.nextInt(10)).forEach(i -> {
            String firstName = userFirstNames.get(random.nextInt(userFirstNames.size()));
            String lastName = userLastNames.get(random.nextInt(userLastNames.size()));
            String emailAddress = generator.generateEmail(firstName, lastName);
            Money budget = new Money(random.nextInt(15));

            User user = User.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .emailAddress(emailAddress)
                    .budget(budget)
                    .build();

            this.userRepository.save(user);
            resultList.add(user);
        });
        return resultList;
    }

    @Override
    public Pet buyPet(Long id, User owner) {
        Pet pet = this.petService.findById(id).orElseThrow(() -> new InvalidPetIdException(id));
        if (pet.getOwner() != null) {
            throw new PetAlreadyHasAnOwnerException(id);
        }
        if (owner.getBudget().getAmount().compareTo(pet.getPrice().getAmount()) < 0) {
            throw new InsufficientAmountException(owner.getId());
        }
        owner.setBudget(owner.getBudget().subtract(pet.getPrice()));
        pet.setOwner(owner);
        owner.getPets().add(pet);
        return this.petService.save(pet);
    }

}
