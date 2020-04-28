package rmiguele.transaction.validation.application;

import io.quarkus.arc.DefaultBean;
import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.command.executor.CreateTransactionCommandExecutor;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.service.ValidateTransactionService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class Executors {

    @ApplicationScoped
    @Produces
    @DefaultBean
    Executor<CreateTransactionCommand> createTransactionCommandExecutor(TransactionRepository transactionRepository, ValidateTransactionService validateTransactionService) {
        return new CreateTransactionCommandExecutor(transactionRepository, validateTransactionService);
    }
}
