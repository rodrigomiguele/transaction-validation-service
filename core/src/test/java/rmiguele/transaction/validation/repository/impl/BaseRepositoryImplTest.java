package rmiguele.transaction.validation.repository.impl;

import dev.morphia.Datastore;
import dev.morphia.query.FieldEnd;
import dev.morphia.query.Query;
import dev.morphia.query.internal.MorphiaCursor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.exception.IdNotPresentException;
import rmiguele.transaction.validation.model.ModelWithId;
import rmiguele.transaction.validation.model.ModelWithoutId;
import rmiguele.transaction.validation.util.IdGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseRepositoryImplTest {

    @Mock
    Datastore datastore;

    @Test
    void testSave() {
        var repository = new ModelWithIdRepository(datastore);
        var model = new ModelWithId();
        model.setId("id");
        model.setValue("value");

        repository.save(model);

        verify(datastore, only()).save(model);
    }

    @Test
    void testSaveGeneratingId() {
        var repository = new ModelWithIdRepository(datastore) {
            @Override
            protected IdGenerator<String, ModelWithId> getIdGenerator() {
                return instance -> "id";
            }
        };
        var model = new ModelWithId();
        model.setValue("value");
        repository.save(model);

        assertEquals("id", model.getId());
    }

    @Test
    void testSaveErrorGeneratingIdIfNoId() {
        var repository = new ModelWithoutIdRepository(datastore) {
            @Override
            protected IdGenerator<String, ModelWithoutId> getIdGenerator() {
                return instance -> "id";
            }
        };
        var model = new ModelWithoutId();
        model.setId("id");
        model.setValue("value");

        assertThrows(IdNotPresentException.class, () -> {
            repository.save(model);
        });
    }

    @Test
    void testFindById() {
        var query = mock(Query.class, "query");
        var field = mock(FieldEnd.class, "field");
        var equal = mock(Query.class, "equal");
        when(datastore.createQuery(ModelWithId.class)).thenReturn(query);
        when(query.field(any())).thenReturn(field);
        when(field.equal(any())).thenReturn(equal);
        var repository = new ModelWithIdRepository(datastore);

        repository.findById("id");

        verify(query, only()).field("id");
        verify(field, only()).equal("id");
        verify(equal, only()).first();
    }

    @Test
    void testErrorFindByIdIfNoId() {
        var query = mock(Query.class, "create");
        when(datastore.createQuery(ModelWithoutId.class)).thenReturn(query);
        var repository = new ModelWithoutIdRepository(datastore);

        assertThrows(IdNotPresentException.class, () -> {
            repository.findById("id");
        });
    }

    @Test
    void testFindAll() {
        var query = mock(Query.class);
        var cursor = mock(MorphiaCursor.class);
        when(datastore.createQuery(ModelWithId.class)).thenReturn(query);
        when(query.find()).thenReturn(cursor);

        var repository = new ModelWithIdRepository(datastore);

        repository.findAll();

        verify(cursor, only()).toList();
    }

}