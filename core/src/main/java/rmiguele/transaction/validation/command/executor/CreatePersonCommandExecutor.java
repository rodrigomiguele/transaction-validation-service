package rmiguele.transaction.validation.command.executor;

import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.model.Person;
import rmiguele.transaction.validation.repository.PersonRepository;

public class CreatePersonCommandExecutor implements Executor<CreatePersonCommand> {

    private PersonRepository personRepository;

    public CreatePersonCommandExecutor(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void execute(CreatePersonCommand command) {
        var person = new Person();
        person.setCode(command.getPersonCode());
        person.setSituation(command.getPersonSituation());
        personRepository.save(person);
    }
}
