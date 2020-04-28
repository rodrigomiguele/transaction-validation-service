package rmiguele.transaction.validation.command;

import rmiguele.transaction.validation.model.PersonSituation;

public class CreatePersonCommand {

    private String personCode;

    private PersonSituation personSituation;

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public PersonSituation getPersonSituation() {
        return personSituation;
    }

    public void setPersonSituation(PersonSituation personSituation) {
        this.personSituation = personSituation;
    }
}
