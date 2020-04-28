package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.Command;
import rmiguele.transaction.validation.command.executor.Executor;

public interface BaseExecutorService<C extends Command> {

    Executor<C> executor();

}
