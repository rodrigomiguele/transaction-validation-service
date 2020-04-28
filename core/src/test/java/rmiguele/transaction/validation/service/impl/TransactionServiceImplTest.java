package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.model.TransactionType;
import rmiguele.transaction.validation.repository.TransactionRepository;

import java.util.Date;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    Executor<CreateTransactionCommand> createTransactionCommandExecutor;

    @Mock
    TransactionRepository transactionRepository;

    private TransactionServiceImpl transactionServiceImpl;

    @Captor
    ArgumentCaptor<Transaction> argumentCaptor;

    @BeforeEach
    void setup() {
        transactionServiceImpl = new TransactionServiceImpl(createTransactionCommandExecutor, transactionRepository);
    }

    @Test
    void saveTransaction() {
        var date = new Date();
        var command = new CreateTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionType(TransactionType.CREDIT_CARD);
        command.setTransactionValue(100.00);
        command.setTransactionDate(date);
        command.setTransactionSenderCode("sender1");
        command.setTransactionReceiverCode("receiver1");

        createTransactionCommandExecutor.execute(command);

        verify(createTransactionCommandExecutor, only()).execute(command);
    }

    @Test
    void getAllTransactions() {
        var transaction = new Transaction();
        transaction.setCode("transaction1");
        transaction.setType(TransactionType.CREDIT_CARD);
        transaction.setValue(100.00);
        transaction.setDate(new Date());
        transaction.setSenderCode("sender1");
        transaction.setReceiverCode("receiver1");
        var transactions = singletonList(transaction);

        given(transactionRepository.findAll()).willReturn(transactions);

        var found = transactionServiceImpl.getTransactions();
        assertEquals(transactions, found);
    }

}