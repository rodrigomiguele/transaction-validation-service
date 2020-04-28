package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(CreateTransactionCommand command);

    List<Transaction> getTransactions();

}
