package rmiguele.transaction.validation.command;

import java.io.Serializable;

public interface Command extends Serializable {

    CommandType getCommandType();

}
