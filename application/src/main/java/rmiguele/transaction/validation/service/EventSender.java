package rmiguele.transaction.validation.service;

import io.vertx.core.eventbus.EventBus;
import rmiguele.transaction.validation.command.Command;
import rmiguele.transaction.validation.event.model.Event;
import rmiguele.transaction.validation.event.repository.EventRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;

@ApplicationScoped
public class EventSender {

    @Inject
    EventBus eventBus;

    @Inject
    EventRepository eventRepository;

    public void send(Command command) {
        eventBus.send(command.getCommandType().name(), command);
        var event = new Event();
        event.setCommand(command);
        event.setDate(new Date());
        eventRepository.save(event);
    }
}
