package rmiguele.transaction.validation.service;

import io.vertx.core.eventbus.EventBus;
import rmiguele.transaction.validation.command.Command;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EventSender {

    @Inject
    EventBus eventBus;

    public void send(Command command) {
        eventBus.send(command.getCommandType().name(), command);
    }
}
