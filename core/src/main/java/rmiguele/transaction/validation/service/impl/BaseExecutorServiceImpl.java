package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.Command;
import rmiguele.transaction.validation.command.executor.Executor;
import rmiguele.transaction.validation.service.BaseExecutorService;

public class BaseExecutorServiceImpl<C extends Command> implements BaseExecutorService<C> {

    private final Executor<C> commandExecutor;

    public BaseExecutorServiceImpl(Executor<C> commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
    public Executor<C> executor() {
        return commandExecutor;
    }
}
