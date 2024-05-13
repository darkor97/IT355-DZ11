package og.dz11.services;

import og.dz11.models.Event;

import java.util.List;

public interface EventService {
    List<Event> GetAll();

    Event GetById(Long eventId);

    Event Add(Event event);

    Event Update(Event event);

    void Delete(Long eventId);
}
