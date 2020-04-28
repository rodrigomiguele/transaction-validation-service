package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.model.Validation;
import rmiguele.transaction.validation.model.ValidationType;
import rmiguele.transaction.validation.repository.ValidationRepository;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidationServiceImplTest {

    @Mock
    Executor<CreateValidationCommand> createValidationCommandExecutor;

    @Mock
    ValidationRepository validationRepository;

    private ValidationServiceImpl validationService;

    @BeforeEach
    void setup() {
        this.validationService = new ValidationServiceImpl(createValidationCommandExecutor, validationRepository);
    }

    @Test
    void saveValidation() {
        var command = new CreateValidationCommand();
        command.setTransactionCode("transaction1");
        command.setValidationType(ValidationType.CREDIT_CARD_VIOLATION);
        command.setMessage("Message");
        validationService.createValidation(command);

        verify(createValidationCommandExecutor, only()).execute(command);
    }

    @Test
    void getValidationsByTransactionCode() {
        var validation1 = new Validation();
        validation1.setCode("validation1");
        validation1.setTransactionCode("transaction1");
        validation1.setType(ValidationType.CREDIT_CARD_SUCCESS);
        var validation2 = new Validation();
        validation2.setCode("validation2");
        validation2.setTransactionCode("transaction1");
        validation2.setType(ValidationType.RESTRICTED_LIST_VIOLATION);
        validation2.setMessage("Message");
        var violations = asList(validation1, validation2);
        given(validationRepository.findByTransactionCode("transaction1")).willReturn(violations);

        var found = validationService.getValidationsByTransactionCode("transaction1");
        assertEquals(violations, found);
    }

    @Test
    void getViolations() {
        var validation1 = new Validation();
        validation1.setCode("validation1");
        validation1.setTransactionCode("transaction1");
        validation1.setType(ValidationType.CREDIT_CARD_SUCCESS);
        var validation2 = new Validation();
        validation2.setCode("validation2");
        validation2.setTransactionCode("transaction2");
        validation2.setType(ValidationType.RESTRICTED_LIST_VIOLATION);
        validation2.setMessage("Message");
        var violations = asList(validation1, validation2);
        given(validationRepository.findAll()).willReturn(violations);

        var found = validationService.getValidations();
        assertEquals(violations, found);
    }
}