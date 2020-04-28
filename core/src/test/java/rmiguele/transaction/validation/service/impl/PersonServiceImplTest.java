package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.model.PersonSituation;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    Executor<CreatePersonCommand> createPersonCommandExecutor;

    PersonServiceImpl personService;

    @BeforeEach
    void setup(){
        personService = new PersonServiceImpl(createPersonCommandExecutor);
    }

    @Test
    void testCreatePerson() {
        CreatePersonCommand command = new CreatePersonCommand();
        command.setPersonCode("code");
        command.setPersonSituation(PersonSituation.LEGAL);

        personService.createPerson(command);

        verify(createPersonCommandExecutor).execute(command);
    }
}