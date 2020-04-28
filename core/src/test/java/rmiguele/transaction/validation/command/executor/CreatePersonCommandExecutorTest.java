package rmiguele.transaction.validation.command.executor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.model.Person;
import rmiguele.transaction.validation.model.PersonSituation;
import rmiguele.transaction.validation.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreatePersonCommandExecutorTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    CreatePersonCommandExecutor executor;

    @Captor
    ArgumentCaptor<Person> argumentCaptor;

    @Test
    public void testCreatePerson() {
        CreatePersonCommand command = new CreatePersonCommand();
        command.setPersonCode("code");
        command.setPersonSituation(PersonSituation.LEGAL);

        executor.execute(command);

        verify(personRepository).save(argumentCaptor.capture());
        Person saved = argumentCaptor.getValue();
        assertEquals("code", saved.getCode());
        assertEquals(PersonSituation.LEGAL, saved.getSituation());
    }

}