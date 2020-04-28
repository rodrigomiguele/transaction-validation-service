package rmiguele.transaction.validation.resource;

import rmiguele.transaction.validation.event.model.Event;
import rmiguele.transaction.validation.event.repository.EventRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("event")
public class EventResource {

    @Inject
    EventRepository eventRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
}
