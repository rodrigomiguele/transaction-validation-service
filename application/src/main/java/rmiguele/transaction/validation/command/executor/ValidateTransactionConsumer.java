package rmiguele.transaction.validation.command.executor;

import io.quarkus.vertx.ConsumeEvent;
import rmiguele.transaction.validation.command.ValidateTransactionCommand;
import rmiguele.transaction.validation.qualifier.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Consumer
public class ValidateTransactionConsumer implements Executor<ValidateTransactionCommand> {

    @Inject
    Executor<ValidateTransactionCommand> original;

    @ConsumeEvent("VALIDATE_TRANSACTION")
    @Override
    public void execute(ValidateTransactionCommand command) {
        original.execute(command);
    }
}
