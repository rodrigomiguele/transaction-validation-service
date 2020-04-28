package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.model.Person;

import java.util.List;

public interface PersonService extends BaseExecutorService<CreatePersonCommand> {

    default void createPerson(CreatePersonCommand command){
        executor().execute(command);
    }

    List<Person> getPersons();
}