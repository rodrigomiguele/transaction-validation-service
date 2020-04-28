package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.model.Validation;

import java.util.List;

public interface ValidationService extends BaseExecutorService<CreateValidationCommand> {

    default void createValidation(CreateValidationCommand command) {
        executor().execute(command);
    }

    List<Validation> getValidations();

    List<Validation> getValidationsByTransactionCode(String transactionCode);

}
