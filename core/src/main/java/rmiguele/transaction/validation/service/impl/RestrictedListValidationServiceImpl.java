package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.CreateValidationCommand;
import rmiguele.transaction.validation.command.RestrictedListValidationCommand;
import rmiguele.transaction.validation.model.PersonSituation;
import rmiguele.transaction.validation.model.ValidationType;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.service.RestrictedListValidationService;
import rmiguele.transaction.validation.service.ValidationService;

import java.util.ArrayList;

public class RestrictedListValidationServiceImpl implements RestrictedListValidationService {

    private final PersonRepository personRepository;

    private final ValidationService validationService;

    public RestrictedListValidationServiceImpl(PersonRepository personRepository, ValidationService validationService) {
        this.personRepository = personRepository;
        this.validationService = validationService;
    }

    @Override
    public void validate(RestrictedListValidationCommand command) {
        var validations = new ArrayList<CreateValidationCommand>();
        personRepository.findById(command.getTransactionSenderCode()).ifPresent(person -> {
            if (PersonSituation.ILLEGAL.equals(person.getSituation())) {
                var createValidationCommand = new CreateValidationCommand();
                createValidationCommand.setTransactionCode(command.getTransactionCode());
                createValidationCommand.setValidationType(ValidationType.RESTRICTED_LIST_VIOLATION);
                createValidationCommand.setMessage(String.format("Remetente \"%s\" em situação ilegal", command.getTransactionSenderCode()));
                validationService.createValidation(createValidationCommand);
                validations.add(createValidationCommand);
            }
        });

        personRepository.findById(command.getTransactionReceiverCode()).ifPresent(person -> {
            if (PersonSituation.ILLEGAL.equals(person.getSituation())) {
                var createValidationCommand = new CreateValidationCommand();
                createValidationCommand.setTransactionCode(command.getTransactionCode());
                createValidationCommand.setValidationType(ValidationType.RESTRICTED_LIST_VIOLATION);
                createValidationCommand.setMessage(String.format("Receptor \"%s\" em situação ilegal", command.getTransactionReceiverCode()));
                validationService.createValidation(createValidationCommand);
                validations.add(createValidationCommand);
            }
        });
        if (validations.isEmpty()) {
            var createValidationCommand = new CreateValidationCommand();
            createValidationCommand.setTransactionCode(command.getTransactionCode());
            createValidationCommand.setValidationType(ValidationType.RESTRICTED_LIST_SUCCESS);
            validationService.createValidation(createValidationCommand);
        }
    }
}
