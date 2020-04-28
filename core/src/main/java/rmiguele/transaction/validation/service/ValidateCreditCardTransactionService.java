package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.ValidateCreditCardTransactionCommand;

public interface ValidateCreditCardTransactionService extends BaseExecutorService<ValidateCreditCardTransactionCommand> {

    default void validate(ValidateCreditCardTransactionCommand command) {
        executor().execute(command);
    }

}
