package scopisto.apprenticeship.petstore.model.exceptions;

public class InvalidPetIdException extends RuntimeException {

    public InvalidPetIdException(Long id) {
        super(String.format("Pet with id %d does not exist.",id));
    }
}
