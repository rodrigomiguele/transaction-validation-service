package rmiguele.transaction.validation.command.executor;

import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.command.ValidateTransactionCommand;
import rmiguele.transaction.validation.model.Transaction;
import rmiguele.transaction.validation.repository.TransactionRepository;
import rmiguele.transaction.validation.service.ValidateTransactionService;

public class CreateTransactionCommandExecutor implements Executor<CreateTransactionCommand> {

    private final TransactionRepository transactionRepository;

    private final ValidateTransactionService validateTransactionService;

    public CreateTransactionCommandExecutor(TransactionRepository transactionRepository, ValidateTransactionService validateTransactionService) {
        this.transactionRepository = transactionRepository;
        this.validateTransactionService = validateTransactionService;
    }

    @Override
    public void execute(CreateTransactionCommand command) {
        var transaction = new Transaction();
        transaction.setCode(command.getTransactionCode());
        transaction.setType(command.getTransactionType());
        transaction.setValue(command.getTransactionValue());
        transaction.setDate(command.getTransactionDate());
        transaction.setSenderCode(command.getTransactionSenderCode());
        transaction.setReceiverCode(command.getTransactionReceiverCode());

        var saved = transactionRepository.save(transaction);

        var validateTransactionCommand = new ValidateTransactionCommand();
        validateTransactionCommand.setTransactionCode(saved.getCode());
        validateTransactionCommand.setTransactionType(saved.getType());
        validateTransactionCommand.setTransactionValue(saved.getValue());
        validateTransactionCommand.setTransactionDate(saved.getDate());
        validateTransactionCommand.setTransactionSenderCode(saved.getSenderCode());
        validateTransactionCommand.setTransactionReceiverCode(saved.getReceiverCode());
        validateTransactionService.validate(validateTransactionCommand);
    }
}
