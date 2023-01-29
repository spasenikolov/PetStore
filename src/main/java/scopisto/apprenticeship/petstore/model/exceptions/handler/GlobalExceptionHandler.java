package scopisto.apprenticeship.petstore.model.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import scopisto.apprenticeship.petstore.model.exceptions.InvalidPetIdException;
import scopisto.apprenticeship.petstore.model.exceptions.InvalidPetInputException;

@ControllerAdvice
public class GlobalExceptionHandler {

    HttpStatus forbidden = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(value = {InvalidPetIdException.class, InvalidPetInputException.class})
    public ResponseEntity<Object> handleInvalidPetIdOrInputException(RuntimeException e) {
        ExceptionRepresentation exception = new ExceptionRepresentation(e.getMessage(), forbidden);
        return new ResponseEntity<>(exception, forbidden);
    }
}
