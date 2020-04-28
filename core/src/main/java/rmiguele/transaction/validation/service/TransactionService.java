package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService extends BaseExecutorService<CreateTransactionCommand> {

    default void createTransaction(CreateTransactionCommand command){
        executor().execute(command);
    }

    List<Transaction> getTransactions();

    Optional<Transaction> getTransaction(String transactionCode);
}
