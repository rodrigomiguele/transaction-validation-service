package rmiguele.transaction.validation.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<K extends Serializable, T extends Serializable> {

    T save(T instance);

    Optional<T> findById(K id);

    List<T> findAll();

}
