package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.model.Person;

public interface PersonService {

    Person createPerson(CreatePersonCommand command);

}