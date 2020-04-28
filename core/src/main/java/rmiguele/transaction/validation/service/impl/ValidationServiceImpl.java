package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.command.executor.CreateValidationCommandExecutor;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.model.Validation;
import rmiguele.transaction.validation.repository.ValidationRepository;
import rmiguele.transaction.validation.service.ValidationService;

import java.util.List;

public class ValidationServiceImpl extends BaseExecutorServiceImpl<CreateValidationCommand> implements ValidationService {

    private final ValidationRepository validationRepository;

    public ValidationServiceImpl(ValidationRepository validationRepository) {
        this(new CreateValidationCommandExecutor(validationRepository), validationRepository);
    }

    public ValidationServiceImpl(Executor<CreateValidationCommand> commandExecutor, ValidationRepository validationRepository) {
        super(commandExecutor);
        this.validationRepository = validationRepository;
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
