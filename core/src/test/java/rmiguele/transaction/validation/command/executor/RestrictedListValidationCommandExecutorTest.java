package rmiguele.transaction.validation.command.executor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.command.RestrictedListValidationCommand;
import rmiguele.transaction.validation.model.Person;
import rmiguele.transaction.validation.model.PersonSituation;
import rmiguele.transaction.validation.model.ValidationType;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.service.ValidationService;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestrictedListValidationCommandExecutorTest {

    @Mock
    PersonRepository personRepository;

    @Mock
    ValidationService validationService;

    @InjectMocks
    RestrictedListValidationCommandExecutor restrictedListValidationCommandExecutor;

    @Captor
    ArgumentCaptor<CreateValidationCommand> argumentCaptor;

    @Test
    void successIfSenderAndReceiverNotFound() {
        given(personRepository.findById("sender1")).willReturn(empty());
        given(personRepository.findById("receiver1")).willReturn(empty());

        var restrictedListValidationCommand = new RestrictedListValidationCommand();
        restrictedListValidationCommand.setTransactionCode("transaction1");
        restrictedListValidationCommand.setTransactionSenderCode("sender1");
        restrictedListValidationCommand.setTransactionReceiverCode("receiver1");
        restrictedListValidationCommandExecutor.execute(restrictedListValidationCommand);

        verify(validationService).createValidation(argumentCaptor.capture());
        CreateValidationCommand value = argumentCaptor.getValue();
        assertEquals("transaction1", value.getTransactionCode());
        assertEquals(ValidationType.RESTRICTED_LIST_SUCCESS, value.getValidationType());
    }

    @Test
    void successIfSenderAndReceiverFoundButNoError() {
        var person1 = new Person();
        person1.setSituation(PersonSituation.LEGAL);
        given(personRepository.findById("sender1")).willReturn(of(person1));

        var person2 = new Person();
        person2.setSituation(PersonSituation.LEGAL);
        given(personRepository.findById("receiver1")).willReturn(of(person2));

        var restrictedListValidationCommand = new RestrictedListValidationCommand();
        restrictedListValidationCommand.setTransactionCode("transaction1");
        restrictedListValidationCommand.setTransactionSenderCode("sender1");
        restrictedListValidationCommand.setTransactionReceiverCode("receiver1");
        restrictedListValidationCommandExecutor.execute(restrictedListValidationCommand);

        verify(validationService).createValidation(argumentCaptor.capture());
        CreateValidationCommand value = argumentCaptor.getValue();
        assertEquals("transaction1", value.getTransactionCode());
        assertEquals(ValidationType.RESTRICTED_LIST_SUCCESS, value.getValidationType());
    }

    @Test
    void addViolationIfSenderIsIllegal() {
        var person1 = new Person();
        person1.setSituation(PersonSituation.ILLEGAL);
        given(personRepository.findById("sender1")).willReturn(of(person1));

        var person2 = new Person();
        person2.setSituation(PersonSituation.LEGAL);
        given(personRepository.findById("receiver1")).willReturn(of(person2));

        given(personRepository.findById("sender1")).willReturn(Optional.of(person1));
        given(personRepository.findById("receiver1")).willReturn(Optional.of(person2));

        var restrictedListValidationCommand = new RestrictedListValidationCommand();
        restrictedListValidationCommand.setTransactionCode("transaction1");
        restrictedListValidationCommand.setTransactionSenderCode("sender1");
        restrictedListValidationCommand.setTransactionReceiverCode("receiver1");
        restrictedListValidationCommandExecutor.execute(restrictedListValidationCommand);

        verify(validationService, only()).createValidation(argumentCaptor.capture());
        CreateValidationCommand value = argumentCaptor.getValue();
        assertEquals("transaction1", value.getTransactionCode());
        assertEquals(ValidationType.RESTRICTED_LIST_VIOLATION, value.getValidationType());
        assertEquals("Remetente \"sender1\" em situação ilegal", value.getMessage());
    }

    @Test
    void addViolationIfReceiverIsIllegal() {
        var person1 = new Person();
        person1.setSituation(PersonSituation.LEGAL);
        given(personRepository.findById("sender1")).willReturn(of(person1));

        var person2 = new Person();
        person2.setSituation(PersonSituation.ILLEGAL);
        given(personRepository.findById("receiver1")).willReturn(of(person2));

        given(personRepository.findById("sender1")).willReturn(Optional.of(person1));
        given(personRepository.findById("receiver1")).willReturn(Optional.of(person2));

        var restrictedListValidationCommand = new RestrictedListValidationCommand();
        restrictedListValidationCommand.setTransactionCode("transaction1");
        restrictedListValidationCommand.setTransactionSenderCode("sender1");
        restrictedListValidationCommand.setTransactionReceiverCode("receiver1");
        restrictedListValidationCommandExecutor.execute(restrictedListValidationCommand);

        verify(validationService, only()).createValidation(argumentCaptor.capture());
        CreateValidationCommand value = argumentCaptor.getValue();
        assertEquals("transaction1", value.getTransactionCode());
        assertEquals(ValidationType.RESTRICTED_LIST_VIOLATION, value.getValidationType());
        assertEquals("Receptor \"receiver1\" em situação ilegal", value.getMessage());
    }

}