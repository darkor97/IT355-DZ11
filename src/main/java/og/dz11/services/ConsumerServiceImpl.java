package og.dz11.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import og.dz11.models.Event;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final EventService eventService;

    @Override
    @JmsListener(destination = "queue")
    public void ReceiveMessage(String message) throws JsonProcessingException {
        var or = new ObjectMapper();
        var eventDecoded = or.readValue(message, Event.class);
        var createdEvent = eventService.Add(eventDecoded);
        System.out.println(or.writer().withDefaultPrettyPrinter().writeValueAsString(createdEvent));
    }
}
