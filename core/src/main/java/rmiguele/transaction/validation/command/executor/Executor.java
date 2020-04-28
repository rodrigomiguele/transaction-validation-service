package rmiguele.transaction.validation.command.executor;

import rmiguele.transaction.validation.command.Command;

@FunctionalInterface
public interface Executor<T extends Command> {

    void execute(T command);

}
