package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.ValidateCreditCardTransactionCommand;
import rmiguele.transaction.validation.command.executor.Executor;

import java.util.Date;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidateCreditCardTransactionServiceImplTest {

    @Mock
    Executor<ValidateCreditCardTransactionCommand> validateCreditCardTransactionCommandExecutor;

    private ValidateCreditCardTransactionServiceImpl validateCreditCardTransactionService;

    @BeforeEach
    void each() {
        this.validateCreditCardTransactionService = new ValidateCreditCardTransactionServiceImpl(validateCreditCardTransactionCommandExecutor);
    }

    @Test
    void validate() {
        var command = new ValidateCreditCardTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionSenderCode("sender1");
        command.setTransactionValue(100.00);
        command.setTransactionDate(new Date());
        validateCreditCardTransactionService.validate(command);

        verify(validateCreditCardTransactionCommandExecutor, only()).execute(command);
    }
}