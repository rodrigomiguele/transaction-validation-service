package rmiguele.transaction.validation.application;

import io.quarkus.arc.DefaultBean;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.repository.ValidationRepository;
import rmiguele.transaction.validation.service.EventProducer;
import rmiguele.transaction.validation.service.RestrictedListValidationService;
import rmiguele.transaction.validation.service.TransactionService;
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService;
import rmiguele.transaction.validation.service.ValidateTransactionService;
import rmiguele.transaction.validation.service.ValidationService;
import rmiguele.transaction.validation.service.impl.RestrictedListValidationServiceImpl;
import rmiguele.transaction.validation.service.impl.TransactionServiceImpl;
import rmiguele.transaction.validation.service.impl.ValidateCreditCardTransactionServiceImpl;
import rmiguele.transaction.validation.service.impl.ValidateTransactionServiceImpl;
import rmiguele.transaction.validation.service.impl.ValidationServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

class Services {

    @ApplicationScoped
    @Produces
    @DefaultBean
    ValidationService validationService(ValidationRepository validationRepository) {
        return new ValidationServiceImpl(validationRepository);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    ValidateCreditCardTransactionService validateCreditCardTransactionService(TransactionRepository transactionRepository, ValidationService validationService) {
        return new ValidateCreditCardTransactionServiceImpl(transactionRepository, validationService);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    RestrictedListValidationService restrictedListValidationService(PersonRepository personRepository, ValidationService validationService) {
        return new RestrictedListValidationServiceImpl(personRepository, validationService);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    ValidateTransactionService validateTransactionService(ValidateCreditCardTransactionService validateCreditCardTransactionService, RestrictedListValidationService restrictedListValidationService) {
        return new ValidateTransactionServiceImpl(validateCreditCardTransactionService, restrictedListValidationService);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    TransactionService transactionService(EventProducer eventProducer, TransactionRepository transactionRepository) {
        return new TransactionServiceImpl(eventProducer::send, transactionRepository);
    }
}
