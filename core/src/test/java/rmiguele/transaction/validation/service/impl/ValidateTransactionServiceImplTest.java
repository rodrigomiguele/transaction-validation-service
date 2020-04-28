package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.ValidateTransactionCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.model.TransactionType;

import java.util.Date;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidateTransactionServiceImplTest {

    @Mock
    Executor<ValidateTransactionCommand> validateTransactionCommandExecutor;

    private ValidateTransactionServiceImpl validateTransactionService;

    @BeforeEach
    void setup() {
        this.validateTransactionService = new ValidateTransactionServiceImpl(validateTransactionCommandExecutor);
    }

    @Test
    void validate() {
        var command = new ValidateTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionType(TransactionType.CREDIT_CARD);
        command.setTransactionValue(100.00);
        command.setTransactionDate(new Date());
        command.setTransactionSenderCode("sender1");
        command.setTransactionReceiverCode("receiver1");
        validateTransactionService.validate(command);
        verify(validateTransactionCommandExecutor, only()).execute(command);
    }
}