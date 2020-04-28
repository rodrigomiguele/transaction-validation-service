package rmiguele.transaction.validation.command.executor;

import io.quarkus.vertx.ConsumeEvent;
import rmiguele.transaction.validation.command.CreateTransactionCommand;
import rmiguele.transaction.validation.qualifier.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Consumer
public class CreateTransactionConsumer implements Executor<CreateTransactionCommand> {

    @Inject
    Executor<CreateTransactionCommand> original;

    @ConsumeEvent("CREATE_TRANSACTION")
    @Override
    public void execute(CreateTransactionCommand command) {
        this.original.execute(command);
    }
}

