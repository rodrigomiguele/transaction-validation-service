package rmiguele.transaction.validation.util;

import java.io.Serializable;

public interface IdGenerator<K extends Serializable, T extends Serializable> {

    K generateId(T instance);

    public static class NoopIdGenerator<K extends Serializable, T extends Serializable> implements IdGenerator<K, T> {

        public static final NoopIdGenerator INSTANCE = new NoopIdGenerator<>();

        @Override
        public K generateId(T instance) {
            return null;
        }
    }
}
