package rmiguele.transaction.validation.repository.impl;

import com.mongodb.MongoClient;
import dev.morphia.query.Sort;
import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.model.TransactionType;
import rmiguele.transaction.validation.repository.TransactionRepository;

import java.util.Optional;

public class TransactionRepositoryImpl extends BaseRepositoryImpl<String, Transaction> implements TransactionRepository {
    public TransactionRepositoryImpl(MongoClient mongoClient) {
        super(Transaction.class, mongoClient);
    }

    @Override
    public Optional<Transaction> findLastTransactionByTypeAndSender(TransactionType transactionType, String transactionSenderCode) {
        var query = query();
        query.field("type").equal(transactionType);
        query.field("senderCode").equal(transactionSenderCode);
        query.order(Sort.descending("date"));
        return Optional.ofNullable(query.first());
    }
}
