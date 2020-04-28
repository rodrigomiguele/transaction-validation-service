package rmiguele.transaction.validation.event.repository.impl;

import com.mongodb.MongoClient;
import rmiguele.transaction.validation.event.model.Event;
import rmiguele.transaction.validation.event.repository.EventRepository;
import rmiguele.transaction.validation.repository.impl.BaseRepositoryImpl;
import rmiguele.transaction.validation.util.IdGenerator;
import rmiguele.transaction.validation.util.UUIDGenerator;

public class EventRepositoryImpl extends BaseRepositoryImpl<String, Event> implements EventRepository {

    public EventRepositoryImpl(MongoClient mongoClient) {
        super(Event.class, mongoClient);
    }

    @Override
    protected IdGenerator<String, Event> getIdGenerator() {
        return new UUIDGenerator<>();
    }
}
