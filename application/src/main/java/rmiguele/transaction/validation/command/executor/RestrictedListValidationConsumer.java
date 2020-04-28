package rmiguele.transaction.validation.command.executor;

import io.quarkus.vertx.ConsumeEvent;
import rmiguele.transaction.validation.command.RestrictedListValidationCommand;
import rmiguele.transaction.validation.qualifier.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Consumer
public class RestrictedListValidationConsumer implements Executor<RestrictedListValidationCommand> {

    @Inject
    Executor<RestrictedListValidationCommand> original;

    @ConsumeEvent("RESTRICTED_LIST_VALIDATION")
    @Override
    public void execute(RestrictedListValidationCommand command) {
        original.execute(command);
    }
}
