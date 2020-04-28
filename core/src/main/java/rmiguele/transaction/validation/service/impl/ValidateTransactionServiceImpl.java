package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.ValidateTransactionCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.command.executor.ValidateTransactionCommandExecutor;
import rmiguele.transaction.validation.service.RestrictedListValidationService;
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService;
import rmiguele.transaction.validation.service.ValidateTransactionService;

public class ValidateTransactionServiceImpl extends BaseExecutorServiceImpl<ValidateTransactionCommand> implements ValidateTransactionService {

    public ValidateTransactionServiceImpl(ValidateCreditCardTransactionService validateCreditCardTransactionService, RestrictedListValidationService restrictedListValidationService) {
        this(new ValidateTransactionCommandExecutor(validateCreditCardTransactionService, restrictedListValidationService));
    }

    public ValidateTransactionServiceImpl(Executor<ValidateTransactionCommand> commandExecutor) {
        super(commandExecutor);
    }


}
