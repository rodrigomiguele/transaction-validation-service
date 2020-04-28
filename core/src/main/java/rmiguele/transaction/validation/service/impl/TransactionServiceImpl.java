package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void createTransaction(CreateTransactionCommand command) {
        var transaction = new Transaction();
        transaction.setCode(command.getTransactionCode());
        transaction.setType(command.getTransactionType());
        transaction.setValue(command.getTransactionValue());
        transaction.setDate(command.getTransactionDate());
        transaction.setSenderCode(command.getTransactionSenderCode());
        transaction.setReceiverCode(command.getTransactionReceiverCode());

        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
}
