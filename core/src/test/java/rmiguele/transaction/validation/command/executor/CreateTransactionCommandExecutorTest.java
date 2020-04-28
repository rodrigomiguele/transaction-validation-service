package rmiguele.transaction.validation.command.executor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.command.ValidateTransactionCommand;
import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.model.TransactionType;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.service.ValidateTransactionService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateTransactionCommandExecutorTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    ValidateTransactionService validateTransactionService;

    @InjectMocks
    CreateTransactionCommandExecutor createTransactionCommandExecutor;

    @Captor
    ArgumentCaptor<Transaction> argumentCaptor;

    @Test
    void saveTransaction() {
        given(transactionRepository.save(any())).willAnswer((Answer<Transaction>) invocation -> invocation.getArgument(0));
        var date = new Date();
        var command = new CreateTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionType(TransactionType.CREDIT_CARD);
        command.setTransactionValue(100.00);
        command.setTransactionDate(date);
        command.setTransactionSenderCode("sender1");
        command.setTransactionReceiverCode("receiver1");

        createTransactionCommandExecutor.execute(command);

        verify(transactionRepository, only()).save(argumentCaptor.capture());
        verify(validateTransactionService, only()).validate(any(ValidateTransactionCommand.class));
        var value = argumentCaptor.getValue();

        assertEquals("transaction1", value.getCode());
        assertEquals(TransactionType.CREDIT_CARD, value.getType());
        assertEquals(100.00, value.getValue());
        assertEquals(date, value.getDate());
        assertEquals("sender1", value.getSenderCode());
        assertEquals("receiver1", value.getReceiverCode());
    }
}