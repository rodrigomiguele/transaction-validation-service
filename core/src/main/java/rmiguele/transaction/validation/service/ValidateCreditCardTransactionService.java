package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.ValidateCreditCardTransactionCommand;

public interface ValidateCreditCardTransactionService {

    void validate(ValidateCreditCardTransactionCommand command);

}
