package scopisto.apprenticeship.petstore.model.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ExceptionRepresentation {

    private final String message;
    private final HttpStatus httpStatus;
}
