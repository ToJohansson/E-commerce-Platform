package tojohansson.customer.exceptions;

import java.util.Optional;

public class EntityNotFoundException extends RuntimeException {


    public EntityNotFoundException(String message) {
        super(message);
    }

    public static <T> T checkNotFound(Optional<T> optional, String entityName, Long id) {
        return optional.orElseThrow(() -> new EntityNotFoundException(entityName + " not found with id: " + id));
    }

}
