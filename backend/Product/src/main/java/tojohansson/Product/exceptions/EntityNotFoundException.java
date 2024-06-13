package tojohansson.Product.exceptions;

import java.util.Optional;

public class EntityNotFoundException {

    public static <T> T checkNotFound(Optional<T> optional, String message) {
        return optional.orElseThrow(() -> new IllegalArgumentException(message));
    }
}
