package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.ValidateCreditCardTransactionCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.command.executor.ValidateCreditCardTransactionCommandExecutor;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService;
import rmiguele.transaction.validation.service.ValidationService;

public class ValidateCreditCardTransactionServiceImpl extends BaseExecutorServiceImpl<ValidateCreditCardTransactionCommand> implements ValidateCreditCardTransactionService {

    public ValidateCreditCardTransactionServiceImpl(TransactionRepository transactionRepository, ValidationService validationService) {
        this(new ValidateCreditCardTransactionCommandExecutor(transactionRepository, validationService));
    }

    public ValidateCreditCardTransactionServiceImpl(Executor<ValidateCreditCardTransactionCommand> commandExecutor) {
        super(commandExecutor);
    }

}
