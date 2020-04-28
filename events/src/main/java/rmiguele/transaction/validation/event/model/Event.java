package rmiguele.transaction.validation.event.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import rmiguele.transaction.validation.command.Command;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Event implements Serializable {

    @Id
    private String code;

    private Command command;

    private Date date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
