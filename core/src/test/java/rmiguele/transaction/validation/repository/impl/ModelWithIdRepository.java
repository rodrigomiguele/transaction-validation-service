package rmiguele.transaction.validation.repository.impl;

import dev.morphia.Datastore;
import rmiguele.transaction.validation.model.ModelWithId;

public class ModelWithIdRepository extends BaseRepositoryImpl<String, ModelWithId> {
    ModelWithIdRepository(Datastore datastore) {
        super(ModelWithId.class, datastore);
    }
}
