package rmiguele.transaction.validation.command.executor;

import io.quarkus.vertx.ConsumeEvent;
import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.qualifier.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Consumer
public class CreatePersonConsumer implements Executor<CreatePersonCommand> {

    @Inject
    Executor<CreatePersonCommand> original;

    @ConsumeEvent("CREATE_PERSON")
    @Override
    public void execute(CreatePersonCommand command) {
        original.execute(command);
    }
}
