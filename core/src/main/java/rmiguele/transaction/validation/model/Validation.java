package rmiguele.transaction.validation.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Validation implements Serializable {

    @Id
    private String code;

    private String transactionCode;

    private ValidationType type;

    private String message;

    private Date date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public ValidationType getType() {
        return type;
    }

    public void setType(ValidationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
