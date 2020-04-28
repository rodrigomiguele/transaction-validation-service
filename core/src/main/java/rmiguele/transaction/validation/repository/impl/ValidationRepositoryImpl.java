package rmiguele.transaction.validation.repository.impl;

import com.mongodb.MongoClient;
import rmiguele.transaction.validation.model.Validation;
import rmiguele.transaction.validation.repository.ValidationRepository;
import rmiguele.transaction.validation.util.IdGenerator;
import rmiguele.transaction.validation.util.UUIDGenerator;

import java.util.List;

public class ValidationRepositoryImpl extends BaseRepositoryImpl<String, Validation> implements ValidationRepository {
    public ValidationRepositoryImpl(MongoClient mongoClient) {
        super(Validation.class, mongoClient);
    }

    @Override
    protected IdGenerator<String, Validation> getIdGenerator() {
        return new UUIDGenerator<>();
    }

    @Override
    public List<Validation> findByTransactionCode(String transactionCode) {
        return query().field("transactionCode").equal(transactionCode).find().toList();
    }
}
