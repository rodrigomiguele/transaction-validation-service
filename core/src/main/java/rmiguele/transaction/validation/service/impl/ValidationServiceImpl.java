package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.model.Validation;
import rmiguele.transaction.validation.repository.ValidationRepository;
import rmiguele.transaction.validation.service.ValidationService;

import java.util.List;

public class ValidationServiceImpl implements ValidationService {

    private final ValidationRepository validationRepository;

    public ValidationServiceImpl(ValidationRepository validationRepository) {
        this.validationRepository = validationRepository;
    }

    @Override
    public void createValidation(CreateValidationCommand command) {
        var validation = new Validation();
        validation.setTransactionCode(command.getTransactionCode());
        validation.setType(command.getValidationType());
        validation.setMessage(command.getMessage());
        validationRepository.save(validation);
    }

    @Override
    public List<Validation> getValidations() {
        return validationRepository.findAll();
    }

    @Override
    public List<Validation> getValidationsByTransactionCode(String transactionCode) {
        return validationRepository.findByTransactionCode(transactionCode);
    }
}
