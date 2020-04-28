package rmiguele.transaction.validation.repository;

import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.model.TransactionType;

import java.util.Optional;

public interface TransactionRepository extends BaseRepository<String, Transaction> {
    Optional<Transaction> findLastTransactionByTypeAndSender(TransactionType creditCard, String transactionSenderCode);
}
