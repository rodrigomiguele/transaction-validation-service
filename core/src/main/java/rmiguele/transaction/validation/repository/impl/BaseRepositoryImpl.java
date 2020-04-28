package rmiguele.transaction.validation.repository.impl;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.annotations.Id;
import dev.morphia.query.Query;
import rmiguele.transaction.validation.exception.IdNotPresentException;
import rmiguele.transaction.validation.exception.SetIdException;
import rmiguele.transaction.validation.repository.BaseRepository;
import rmiguele.transaction.validation.util.IdGenerator;
import rmiguele.transaction.validation.util.IdGenerator.NoopIdGenerator;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

public abstract class BaseRepositoryImpl<K extends Serializable, T extends Serializable> implements BaseRepository<K, T> {

    private final Class<T> clazz;

    private final Datastore datastore;

    BaseRepositoryImpl(Class<T> clazz, MongoClient mongoClient) {
        this.clazz = clazz;
        var morphia = new Morphia();
        morphia.map(clazz);
        this.datastore = morphia.createDatastore(mongoClient, clazz.getSimpleName());
        this.datastore.ensureIndexes();
    }

    BaseRepositoryImpl(Class<T> clazz, Datastore datastore) {
        this.clazz = clazz;
        this.datastore = datastore;
    }

    @Override
    public T save(T instance) {
        if (getIdGenerator() != NoopIdGenerator.INSTANCE) {
            setId(instance);
        }
        datastore.save(instance);
        return instance;
    }

    @Override
    public Optional<T> findById(K id) {
        return Optional.ofNullable(datastore.createQuery(clazz)
                .field(getIdField().getName()).equal(id)
                .first());
    }

    @Override
    public List<T> findAll() {
        return datastore.createQuery(clazz).find().toList();
    }

    private void setId(T instance) {
        try {
            var idField = getIdField();
            idField.setAccessible(true);
            idField.set(instance, getIdGenerator().generateId(instance));
            idField.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new SetIdException(clazz, e);
        }
    }

    private Field getIdField() {
        return stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst().orElseThrow(() -> new IdNotPresentException(clazz));
    }

    Query<T> query() {
        return datastore.createQuery(clazz);
    }

    protected IdGenerator<K, T> getIdGenerator() {
        return NoopIdGenerator.INSTANCE;
    }

}
