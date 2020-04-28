package rmiguele.transaction.validation.command.executor;

import io.quarkus.vertx.ConsumeEvent;
import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.qualifier.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Consumer
public class CreateValidationConsumer implements Executor<CreateValidationCommand> {

    @Inject
    Executor<CreateValidationCommand> original;

    @ConsumeEvent("CREATE_VALIDATION")
    @Override
    public void execute(CreateValidationCommand command) {
        original.execute(command);
    }
}
