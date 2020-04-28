package rmiguele.transaction.validation.application;

import io.quarkus.arc.DefaultBean;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.repository.ValidationRepository;
import rmiguele.transaction.validation.service.EventSender;
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
    ValidationService validationService(EventSender eventSender, ValidationRepository validationRepository) {
        return new ValidationServiceImpl(eventSender::send, validationRepository);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    ValidateCreditCardTransactionService validateCreditCardTransactionService(EventSender eventSender) {
        return new ValidateCreditCardTransactionServiceImpl(eventSender::send);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    RestrictedListValidationService restrictedListValidationService(EventSender eventSender) {
        return new RestrictedListValidationServiceImpl(eventSender::send);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    ValidateTransactionService validateTransactionService(EventSender eventSender, ValidateCreditCardTransactionService validateCreditCardTransactionService, RestrictedListValidationService restrictedListValidationService) {
        return new ValidateTransactionServiceImpl(eventSender::send);
    }

    @ApplicationScoped
    @Produces
    @DefaultBean
    TransactionService transactionService(EventSender eventSender, TransactionRepository transactionRepository) {
        return new TransactionServiceImpl(eventSender::send, transactionRepository);
    }
}
