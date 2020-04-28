package rmiguele.transaction.validation.repository.impl;

import com.mongodb.MongoClient;
import dev.morphia.query.FindOptions;
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
        var transactions = query.find(new FindOptions().limit(2)).toList();
        if (transactions.size() == 2) {
            return Optional.of(transactions.get(1));
        }
        return Optional.empty();
    }
}
