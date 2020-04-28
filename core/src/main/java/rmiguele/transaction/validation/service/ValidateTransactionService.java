package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.ValidateTransactionCommand;

public interface ValidateTransactionService extends BaseExecutorService<ValidateTransactionCommand>{

    default void validate(ValidateTransactionCommand command){
        executor().execute(command);
    }

}
