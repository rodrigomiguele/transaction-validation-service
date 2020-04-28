package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.command.executor.CreateTransactionCommandExecutor;
import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.service.TransactionService;
import rmiguele.transaction.validation.service.ValidateTransactionService;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl extends BaseExecutorServiceImpl<CreateTransactionCommand> implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, ValidateTransactionService validateTransactionService) {
        this(new CreateTransactionCommandExecutor(transactionRepository, validateTransactionService), transactionRepository);
    }

    public TransactionServiceImpl(Executor<CreateTransactionCommand> commandExecutor, TransactionRepository transactionRepository) {
        super(commandExecutor);
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransaction(String transactionCode) {
        return transactionRepository.findById(transactionCode);
    }
}
