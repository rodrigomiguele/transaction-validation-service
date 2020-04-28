package rmiguele.transaction.validation.repository.impl;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import rmiguele.transaction.validation.model.ModelWithoutId;

public class ModelWithoutIdRepository extends BaseRepositoryImpl<String, ModelWithoutId> {
    ModelWithoutIdRepository(Datastore datastore) {
        super(ModelWithoutId.class, datastore);
    }
}
