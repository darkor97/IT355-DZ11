package og.dz11.services;

import lombok.RequiredArgsConstructor;
import og.dz11.models.Event;
import og.dz11.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public List<Event> GetAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event GetById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    @Override
    public Event Add(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event Update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void Delete(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
