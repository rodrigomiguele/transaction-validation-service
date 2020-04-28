package rmiguele.transaction.validation.service.impl;

import rmiguele.transaction.validation.command.CreatePersonCommand;
import rmiguele.transaction.validation.model.Person;
import rmiguele.transaction.validation.repository.PersonRepository;
import rmiguele.transaction.validation.service.PersonService;

public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void createPerson(CreatePersonCommand command) {
        var person = new Person();
        person.setCode(command.getPersonCode());
        person.setSituation(command.getPersonSituation());
        personRepository.save(person);
    }
}
