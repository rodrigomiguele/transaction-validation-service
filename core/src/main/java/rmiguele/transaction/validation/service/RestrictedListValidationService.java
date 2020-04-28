package rmiguele.transaction.validation.service;

import rmiguele.transaction.validation.command.RestrictedListValidationCommand;

public interface RestrictedListValidationService {

    void validate(RestrictedListValidationCommand command);

}
