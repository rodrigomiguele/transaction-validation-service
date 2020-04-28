package rmiguele.transaction.validation.vo;

import rmiguele.transaction.validation.model.PersonSituation;

import javax.validation.constraints.NotNull;

public class CreatePersonVO {

    @NotNull
    private PersonSituation personSituation;

    public PersonSituation getPersonSituation() {
        return personSituation;
    }

    public void setPersonSituation(PersonSituation personSituation) {
        this.personSituation = personSituation;
    }
}
