package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.model.TransactionType;
import rmiguele.transaction.validation.repository.TransactionRepository;

import java.util.Date;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;

    @Captor
    ArgumentCaptor<Transaction> argumentCaptor;

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

        transactionServiceImpl.createTransaction(command);

        verify(transactionRepository, Mockito.only()).save(argumentCaptor.capture());
        var value = argumentCaptor.getValue();

        assertEquals("transaction1", value.getCode());
        assertEquals(TransactionType.CREDIT_CARD, value.getType());
        assertEquals(100.00, value.getValue());
        assertEquals(date, value.getDate());
        assertEquals("sender1", value.getSenderCode());
        assertEquals("receiver1", value.getReceiverCode());
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