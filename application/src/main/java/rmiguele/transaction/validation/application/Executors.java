package rmiguele.transaction.validation.application;

import io.quarkus.arc.DefaultBean;
import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.command.RestrictedListValidationCommand;
import rmiguele.transaction.validation.command.ValidateCreditCardTransactionCommand;
import rmiguele.transaction.validation.command.ValidateTransactionCommand;
import rmiguele.transaction.validation.command.executor.CreatePersonCommandExecutor;
import rmiguele.transaction.validation.command.executor.CreateTransactionCommandExecutor;
import rmiguele.transaction.validation.command.executor.CreateValidationCommandExecutor;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.command.executor.RestrictedListValidationCommandExecutor;
import rmiguele.transaction.validation.command.executor.ValidateCreditCardTransactionCommandExecutor;
import rmiguele.transaction.validation.command.executor.ValidateTransactionCommandExecutor;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.repository.ValidationRepository;
import rmiguele.transaction.validation.service.RestrictedListValidationService;
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService;
import rmiguele.transaction.validation.service.ValidateTransactionService;
import rmiguele.transaction.validation.service.ValidationService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class Executors {

    @ApplicationScoped
    @Produces
    @DefaultBean
    Executor<CreateTransactionCommand> createTransactionCommandExecutor(TransactionRepository transactionRepository, ValidateTransactionService validateTransactionService) {
        return new CreateTransactionCommandExecutor(transactionRepository, validateTransactionService);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    Executor<CreateValidationCommand> createValidationCommandExecutor(ValidationRepository validationRepository) {
        return new CreateValidationCommandExecutor(validationRepository);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    Executor<ValidateCreditCardTransactionCommand> validateCreditCardTransactionCommandExecutor(TransactionRepository transactionRepository, ValidationService validationService) {
        return new ValidateCreditCardTransactionCommandExecutor(transactionRepository, validationService);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    Executor<RestrictedListValidationCommand> restrictedListValidationCommandExecutor(PersonRepository personRepository, ValidationService validationService) {
        return new RestrictedListValidationCommandExecutor(personRepository, validationService);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    Executor<ValidateTransactionCommand> validateTransactionCommandExecutor(ValidateCreditCardTransactionService validateCreditCardTransactionService, RestrictedListValidationService restrictedListValidationService) {
        return new ValidateTransactionCommandExecutor(validateCreditCardTransactionService, restrictedListValidationService);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    Executor<CreatePersonCommand> createPersonCommandExecutor(PersonRepository personRepository) {
        return new CreatePersonCommandExecutor(personRepository);
    }
}
