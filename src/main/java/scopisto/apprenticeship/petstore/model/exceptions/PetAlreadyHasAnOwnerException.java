package scopisto.apprenticeship.petstore.model.exceptions;

public class PetAlreadyHasAnOwnerException extends RuntimeException {
    public PetAlreadyHasAnOwnerException(Long id) {
        super(String.format("Pet with id %d already has an owner.",id));
    }
}
