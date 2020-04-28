package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.command.executor.CreatePersonCommandExecutor;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.service.PersonService;

public class PersonServiceImpl extends BaseExecutorServiceImpl<CreatePersonCommand> implements PersonService {

    public PersonServiceImpl(PersonRepository personRepository) {
        this(new CreatePersonCommandExecutor(personRepository));
    }

    public PersonServiceImpl(Executor<CreatePersonCommand> commandExecutor) {
        super(commandExecutor);
    }
}
