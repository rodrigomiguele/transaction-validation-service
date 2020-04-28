package rmiguele.transaction.validation.command.executor;

import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.model.Validation;
import rmiguele.transaction.validation.repository.ValidationRepository;

import java.util.Date;

public class CreateValidationCommandExecutor implements Executor<CreateValidationCommand> {

    private final ValidationRepository validationRepository;

    public CreateValidationCommandExecutor(ValidationRepository validationRepository) {
        this.validationRepository = validationRepository;
    }

    @Override
    public void execute(CreateValidationCommand command) {
        var validation = new Validation();
        validation.setTransactionCode(command.getTransactionCode());
        validation.setType(command.getValidationType());
        validation.setMessage(command.getMessage());
        validation.setDate(new Date());
        validationRepository.save(validation);
    }

}
