package rmiguele.transaction.validation.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.io.Serializable;

@Entity
public class ModelWithId implements Serializable {
    @Id
    private String id;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}