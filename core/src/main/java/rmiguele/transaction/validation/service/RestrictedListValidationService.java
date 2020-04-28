package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.RestrictedListValidationCommand;

public interface RestrictedListValidationService extends BaseExecutorService<RestrictedListValidationCommand> {

    default void validate(RestrictedListValidationCommand command) {
        executor().execute(command);
    }

}
