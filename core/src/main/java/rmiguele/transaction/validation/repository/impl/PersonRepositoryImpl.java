package rmiguele.transaction.validation.repository.impl;

import com.mongodb.MongoClient;
import rmiguele.transaction.validation.model.Person;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.util.IdGenerator;
import rmiguele.transaction.validation.util.UUIDGenerator;

public class PersonRepositoryImpl extends BaseRepositoryImpl<String, Person> implements PersonRepository {
    public PersonRepositoryImpl(MongoClient mongoClient) {
        super(Person.class, mongoClient);
    }

    @Override
    protected IdGenerator<String, Person> getIdGenerator() {
        return new UUIDGenerator<>();
    }
}
