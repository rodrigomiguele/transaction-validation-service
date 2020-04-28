package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.ValidateTransactionCommand;

public interface ValidateTransactionService {

    void validate(ValidateTransactionCommand command);

}
