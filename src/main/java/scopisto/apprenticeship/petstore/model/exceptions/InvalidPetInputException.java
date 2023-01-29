package scopisto.apprenticeship.petstore.model.exceptions;

public class InvalidPetInputException extends RuntimeException{

    public InvalidPetInputException(String petType) {
        super(String.format("Can't add a %s, only a dog or a cat for the moment.", petType));
    }
}
