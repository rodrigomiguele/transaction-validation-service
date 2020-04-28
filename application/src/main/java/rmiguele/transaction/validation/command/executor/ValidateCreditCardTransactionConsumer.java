package rmiguele.transaction.validation.command.executor;

import io.quarkus.vertx.ConsumeEvent;
import rmiguele.transaction.validation.command.ValidateCreditCardTransactionCommand;
import rmiguele.transaction.validation.qualifier.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Consumer
public class ValidateCreditCardTransactionConsumer implements Executor<ValidateCreditCardTransactionCommand> {

    @Inject
    Executor<ValidateCreditCardTransactionCommand> original;

    @ConsumeEvent("VALIDATE_CREDIT_CARD_TRANSACTION")
    @Override
    public void execute(ValidateCreditCardTransactionCommand command) {
        original.execute(command);
    }
}
