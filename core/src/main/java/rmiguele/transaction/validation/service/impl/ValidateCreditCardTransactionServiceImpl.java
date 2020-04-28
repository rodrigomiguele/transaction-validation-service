package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.command.ValidateCreditCardTransactionCommand;
import rmiguele.transaction.validation.model.TransactionType;
import rmiguele.transaction.validation.model.ValidationType;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService;
import rmiguele.transaction.validation.service.ValidationService;

import java.util.ArrayList;

public class ValidateCreditCardTransactionServiceImpl implements ValidateCreditCardTransactionService {

    private static Long FIVE_MINUTES = 5 * 60 * 1000L;

    private final TransactionRepository transactionRepository;

    private final ValidationService validationService;

    public ValidateCreditCardTransactionServiceImpl(TransactionRepository transactionRepository, ValidationService validationService) {
        this.transactionRepository = transactionRepository;
        this.validationService = validationService;
    }

    @Override
    public void validate(ValidateCreditCardTransactionCommand command) {
        var validations = new ArrayList<CreateValidationCommand>();
        var transaction = transactionRepository.findLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, command.getTransactionSenderCode());
        transaction.ifPresent(found -> {
            if (command.getTransactionValue().equals(found.getValue()) && command.getTransactionDate().getTime() - found.getDate().getTime() <= FIVE_MINUTES) {
                var createValidationCommand = new CreateValidationCommand();
                createValidationCommand.setTransactionCode(command.getTransactionCode());
                createValidationCommand.setValidationType(ValidationType.CREDIT_CARD_VIOLATION);
                createValidationCommand.setMessage("Uma transação de mesmo valor já ocorreu nos últimos 5 minutos.");
                validationService.createValidation(createValidationCommand);
                validations.add(createValidationCommand);
            }
        });
        if (validations.isEmpty()) {
            var createValidationCommand = new CreateValidationCommand();
            createValidationCommand.setTransactionCode(command.getTransactionCode());
            createValidationCommand.setValidationType(ValidationType.CREDIT_CARD_SUCCESS);
            validationService.createValidation(createValidationCommand);
        }
    }
}
