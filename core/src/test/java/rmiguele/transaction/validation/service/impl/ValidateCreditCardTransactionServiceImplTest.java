package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.command.ValidateCreditCardTransactionCommand;
import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.model.TransactionType;
import rmiguele.transaction.validation.model.ValidationType;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.service.ValidationService;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidateCreditCardTransactionServiceImplTest {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    ValidationService validationService;

    @InjectMocks
    ValidateCreditCardTransactionServiceImpl validateCreditCardTransactionService;

    @Captor
    ArgumentCaptor<CreateValidationCommand> argumentCaptor;

    @Test
    void successIfNoPreviousTransactionFromSender() {
        given(transactionRepository.findLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, "sender1")).willReturn(empty());
        var command = new ValidateCreditCardTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionSenderCode("sender1");
        command.setTransactionValue(100.00);
        command.setTransactionDate(new Date());
        validateCreditCardTransactionService.validate(command);

        verify(validationService, only()).createValidation(argumentCaptor.capture());
        var value = argumentCaptor.getValue();
        assertEquals("transaction1", value.getTransactionCode());
        assertEquals(ValidationType.CREDIT_CARD_SUCCESS, value.getValidationType());
    }

    @Test
    void successIfPreviousTransactionHasNotTheSameValue() {
        var previousTransaction = new Transaction();
        previousTransaction.setCode("transaction1");
        previousTransaction.setType(TransactionType.CREDIT_CARD);
        previousTransaction.setValue(100.00);
        previousTransaction.setDate(new Date());
        previousTransaction.setSenderCode("sender1");
        previousTransaction.setReceiverCode("receiver1");

        given(transactionRepository.findLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, "sender1")).willReturn(Optional.of(previousTransaction));

        var command = new ValidateCreditCardTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionSenderCode("sender1");
        command.setTransactionValue(200.00);
        command.setTransactionDate(new Date());
        validateCreditCardTransactionService.validate(command);

        verify(validationService, only()).createValidation(argumentCaptor.capture());
        var value = argumentCaptor.getValue();
        assertEquals("transaction1", value.getTransactionCode());
        assertEquals(ValidationType.CREDIT_CARD_SUCCESS, value.getValidationType());
    }

    @Test
    void successIfPreviousTransactionHasTheSameValueButAfter5Minutes() {
        var date = new Date();
        var previousDate = addMinutes(date, -6);
        var previousTransaction = new Transaction();
        previousTransaction.setCode("transaction1");
        previousTransaction.setType(TransactionType.CREDIT_CARD);
        previousTransaction.setValue(100.00);
        previousTransaction.setDate(previousDate);
        previousTransaction.setSenderCode("sender1");
        previousTransaction.setReceiverCode("receiver1");

        given(transactionRepository.findLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, "sender1")).willReturn(Optional.of(previousTransaction));

        var command = new ValidateCreditCardTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionSenderCode("sender1");
        command.setTransactionValue(200.00);
        command.setTransactionDate(date);
        validateCreditCardTransactionService.validate(command);

        verify(validationService, only()).createValidation(argumentCaptor.capture());
        var value = argumentCaptor.getValue();
        assertEquals("transaction1", value.getTransactionCode());
        assertEquals(ValidationType.CREDIT_CARD_SUCCESS, value.getValidationType());
    }

    @Test
    void addViolationIfPreviousTransactionHasTheSameValueButBefore5Minutes() {
        var date = new Date();
        var previousDate = addMinutes(date, -4);
        var previousTransaction = new Transaction();
        previousTransaction.setCode("transaction1");
        previousTransaction.setType(TransactionType.CREDIT_CARD);
        previousTransaction.setValue(100.00);
        previousTransaction.setDate(previousDate);
        previousTransaction.setSenderCode("sender1");
        previousTransaction.setReceiverCode("receiver1");

        given(transactionRepository.findLastTransactionByTypeAndSender(TransactionType.CREDIT_CARD, "sender1")).willReturn(Optional.of(previousTransaction));

        var command = new ValidateCreditCardTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionSenderCode("sender1");
        command.setTransactionValue(100.00);
        command.setTransactionDate(date);
        validateCreditCardTransactionService.validate(command);

        verify(validationService, only()).createValidation(argumentCaptor.capture());
        var value = argumentCaptor.getValue();
        assertEquals("transaction1", value.getTransactionCode());
        assertEquals(ValidationType.CREDIT_CARD_VIOLATION, value.getValidationType());
        assertEquals("Uma transação de mesmo valor já ocorreu nos últimos 5 minutos.", value.getMessage());
    }

    private Date addMinutes(Date date, Integer minutes) {
        var calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}