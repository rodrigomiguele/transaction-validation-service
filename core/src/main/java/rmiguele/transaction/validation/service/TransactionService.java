package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.model.Transaction;

import java.util.List;

public interface TransactionService {

    void createTransaction(CreateTransactionCommand command);

    List<Transaction> getTransactions();

}
