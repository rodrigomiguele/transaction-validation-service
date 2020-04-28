package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.model.PersonSituation;
import rmiguele.transaction.validation.repository.PersonRepository;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    Executor<CreatePersonCommand> createPersonCommandExecutor;

    @Mock
    PersonRepository personRepository;

    PersonServiceImpl personService;

    @BeforeEach
    void setup() {
        personService = new PersonServiceImpl(createPersonCommandExecutor, personRepository);
    }

    @Test
    void testCreatePerson() {
        CreatePersonCommand command = new CreatePersonCommand();
        command.setPersonCode("code");
        command.setPersonSituation(PersonSituation.LEGAL);

        personService.createPerson(command);

        verify(createPersonCommandExecutor).execute(command);
    }

    @Test
    void getPersons() {
        personService.getPersons();
        verify(personRepository, only()).findAll();
    }
}