package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.CreatePersonCommand;

public interface PersonService extends BaseExecutorService<CreatePersonCommand> {

    default void createPerson(CreatePersonCommand command){
        executor().execute(command);
    }

}