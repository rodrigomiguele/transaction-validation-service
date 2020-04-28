package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.CreatePersonCommand;

public interface PersonService {

    void createPerson(CreatePersonCommand command);

}