package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.model.Validation;

import java.util.List;

public interface ValidationService {

    Validation createValidation(CreateValidationCommand command);

    List<Validation> getValidations();

    List<Validation> getValidationsByTransactionCode(String transactionCode);

}
