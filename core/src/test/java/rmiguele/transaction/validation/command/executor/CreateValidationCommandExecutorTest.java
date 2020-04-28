package rmiguele.transaction.validation.command.executor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.model.Validation;
import rmiguele.transaction.validation.model.ValidationType;
import rmiguele.transaction.validation.repository.ValidationRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateValidationCommandExecutorTest {

    @Mock
    ValidationRepository validationRepository;

    @InjectMocks
    CreateValidationCommandExecutor createValidationCommandExecutor;

    @Captor
    ArgumentCaptor<Validation> argumentCaptor;

    @Test
    void saveValidation() {
        var command = new CreateValidationCommand();
        command.setTransactionCode("transaction1");
        command.setValidationType(ValidationType.CREDIT_CARD_VIOLATION);
        command.setMessage("Message");
        createValidationCommandExecutor.execute(command);

        verify(validationRepository, only()).save(argumentCaptor.capture());
        var value = argumentCaptor.getValue();
        assertEquals("transaction1", value.getTransactionCode());
        assertEquals(ValidationType.CREDIT_CARD_VIOLATION, value.getType());
        assertEquals("Message", value.getMessage());
    }
}