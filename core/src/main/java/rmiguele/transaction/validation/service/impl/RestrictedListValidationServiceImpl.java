package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.RestrictedListValidationCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.command.executor.RestrictedListValidationCommandExecutor;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.service.RestrictedListValidationService;
import rmiguele.transaction.validation.service.ValidationService;

public class RestrictedListValidationServiceImpl extends BaseExecutorServiceImpl<RestrictedListValidationCommand> implements RestrictedListValidationService {

    public RestrictedListValidationServiceImpl(PersonRepository personRepository, ValidationService validationService) {
        this(new RestrictedListValidationCommandExecutor(personRepository, validationService));
    }

    public RestrictedListValidationServiceImpl(Executor<RestrictedListValidationCommand> commandExecutor) {
        super(commandExecutor);
    }

}
