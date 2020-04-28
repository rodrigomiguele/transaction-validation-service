package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.RestrictedListValidationCommand;
import rmiguele.transaction.validation.command.ValidateCreditCardTransactionCommand;
import rmiguele.transaction.validation.command.ValidateTransactionCommand;
import rmiguele.transaction.validation.model.TransactionType;
import rmiguele.transaction.validation.service.RestrictedListValidationService;
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService;
import rmiguele.transaction.validation.service.ValidateTransactionService;

public class ValidateTransactionServiceImpl implements ValidateTransactionService {

    private final ValidateCreditCardTransactionService validateCreditCardTransactionService;

    private final RestrictedListValidationService restrictedListValidationService;

    public ValidateTransactionServiceImpl(ValidateCreditCardTransactionService validateCreditCardTransactionService, RestrictedListValidationService restrictedListValidationService) {
        this.validateCreditCardTransactionService = validateCreditCardTransactionService;
        this.restrictedListValidationService = restrictedListValidationService;
    }

    @Override
    public void validate(ValidateTransactionCommand command) {
        sleep(5000);
        if (TransactionType.CREDIT_CARD.equals(command.getTransactionType())) {
            var validateCreditCardTransactionCommand = new ValidateCreditCardTransactionCommand();
            validateCreditCardTransactionCommand.setTransactionCode(command.getTransactionCode());
            validateCreditCardTransactionCommand.setTransactionSenderCode(command.getTransactionSenderCode());
            validateCreditCardTransactionCommand.setTransactionValue(command.getTransactionValue());
            validateCreditCardTransactionCommand.setTransactionDate(command.getTransactionDate());
            validateCreditCardTransactionService.validate(validateCreditCardTransactionCommand);
        }
        var restrictedListValidationCommand = new RestrictedListValidationCommand();
        restrictedListValidationCommand.setTransactionCode(command.getTransactionCode());
        restrictedListValidationCommand.setTransactionSenderCode(command.getTransactionSenderCode());
        restrictedListValidationCommand.setTransactionReceiverCode(command.getTransactionReceiverCode());
        restrictedListValidationService.validate(restrictedListValidationCommand);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
