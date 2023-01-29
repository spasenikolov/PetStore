package scopisto.apprenticeship.petstore.model.exceptions;

public class InsufficientAmountException extends RuntimeException {

    public InsufficientAmountException(Long id) {
        super(String.format("Owner with id %d does not have enough money to buy the pet.", id));
    }
}
