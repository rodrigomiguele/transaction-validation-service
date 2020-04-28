package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.RestrictedListValidationCommand;
import rmiguele.transaction.validation.command.executor.Executor;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RestrictedListValidationServiceImplTest {

    @Mock
    Executor<RestrictedListValidationCommand> restrictedListValidationCommandExecutor;

    private RestrictedListValidationServiceImpl restrictedListValidationService;

    @BeforeEach
    void setup() {
        restrictedListValidationService = new RestrictedListValidationServiceImpl(restrictedListValidationCommandExecutor);
    }

    @Test
    void validate() {
        var restrictedListValidationCommand = new RestrictedListValidationCommand();
        restrictedListValidationCommand.setTransactionCode("transaction1");
        restrictedListValidationCommand.setTransactionSenderCode("sender1");
        restrictedListValidationCommand.setTransactionReceiverCode("receiver1");
        restrictedListValidationService.validate(restrictedListValidationCommand);

        verify(restrictedListValidationCommandExecutor).execute(restrictedListValidationCommand);
    }
}