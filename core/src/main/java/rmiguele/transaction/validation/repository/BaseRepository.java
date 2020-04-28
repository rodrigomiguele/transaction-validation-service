package rmiguele.transaction.validation.repository;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<K extends Serializable, T extends Serializable> {

    T save(T instance);

    T findById(K id);

    List<T> findAll();

}
