package rmiguele.transaction.validation.util;

import java.io.Serializable;
import java.util.UUID;

public class UUIDGenerator<T extends Serializable> implements IdGenerator<String, T> {

    @Override
    public String generateId(T instance) {
        return UUID.randomUUID().toString();
    }

}
