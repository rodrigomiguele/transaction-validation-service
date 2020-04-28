package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.command.executor.CreatePersonCommandExecutor;
import rmiguele.transaction.validation.model.Person;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.service.PersonService;

import java.util.List;

public class PersonServiceImpl extends BaseExecutorServiceImpl<CreatePersonCommand> implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this(new CreatePersonCommandExecutor(personRepository), personRepository);
    }

    public PersonServiceImpl(Executor<CreatePersonCommand> commandExecutor, PersonRepository personRepository) {
        super(commandExecutor);
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }
}
