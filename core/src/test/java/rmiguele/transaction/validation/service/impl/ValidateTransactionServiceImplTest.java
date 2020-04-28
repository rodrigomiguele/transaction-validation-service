package rmiguele.transaction.validation.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rmiguele.transaction.validation.command.ValidateTransactionCommand;
import rmiguele.transaction.validation.model.TransactionType;
import rmiguele.transaction.validation.service.RestrictedListValidationService;
import rmiguele.transaction.validation.service.ValidateCreditCardTransactionService;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ValidateTransactionServiceImplTest {

    @Mock
    ValidateCreditCardTransactionService validateCreditCardTransactionService;

    @Mock
    RestrictedListValidationService restrictedListValidationService;

    @InjectMocks
    ValidateTransactionServiceImpl validateTransactionService;

    @Test
    void validateCreditCard() {
        var command = new ValidateTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionType(TransactionType.CREDIT_CARD);
        command.setTransactionValue(100.00);
        command.setTransactionDate(new Date());
        command.setTransactionSenderCode("sender1");
        command.setTransactionReceiverCode("receiver1");
        validateTransactionService.validate(command);
        verify(validateCreditCardTransactionService, only()).validate(any());
        verify(restrictedListValidationService, only()).validate(any());
    }

    @Test
    void doesNotValidateCreditCardForOtherTypes() {
        var command = new ValidateTransactionCommand();
        command.setTransactionCode("transaction1");
        command.setTransactionType(TransactionType.ATM);
        command.setTransactionValue(100.00);
        command.setTransactionDate(new Date());
        command.setTransactionSenderCode("sender1");
        command.setTransactionReceiverCode("receiver1");
        validateTransactionService.validate(command);
        verify(validateCreditCardTransactionService, never()).validate(any());
        verify(restrictedListValidationService, only()).validate(any());
    }
}