package rmiguele.transaction.validation.application;

import com.mongodb.MongoClient;
import io.quarkus.arc.DefaultBean;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.repository.ValidationRepository;
import rmiguele.transaction.validation.repository.impl.PersonRepositoryImpl;
import rmiguele.transaction.validation.repository.impl.TransactionRepositoryImpl;
import rmiguele.transaction.validation.repository.impl.ValidationRepositoryImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

class Repositories {

    @ApplicationScoped
    @Produces
    @DefaultBean
    TransactionRepository transactionRepository(MongoClient mongoClient) {
        return new TransactionRepositoryImpl(mongoClient);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    ValidationRepository validationRepository(MongoClient mongoClient) {
        return new ValidationRepositoryImpl(mongoClient);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    PersonRepository personRepository(MongoClient mongoClient) {
        return new PersonRepositoryImpl(mongoClient);
    }
}
