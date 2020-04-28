package rmiguele.transaction.validation.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.io.Serializable;

@Entity
public class Person implements Serializable {

    @Id
    private String code;

    private PersonSituation situation;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PersonSituation getSituation() {
        return situation;
    }

    public void setSituation(PersonSituation situation) {
        this.situation = situation;
    }
}
