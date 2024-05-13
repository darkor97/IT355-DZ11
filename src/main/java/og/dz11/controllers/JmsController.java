package og.dz11.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import og.dz11.models.Event;
import og.dz11.services.EventService;
import og.dz11.services.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jms")
@RequiredArgsConstructor
public class JmsController {
    private final ProducerService producerService;
    private final EventService eventService;

    // Slanje objekta kao json na slusaoca
    @GetMapping("/{message}")
    public ResponseEntity<String> PublishMessage(@PathVariable("message") String message) throws JsonProcessingException {
        var event = new Event();
        event.Name = message;
        event.Id = 0L;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String messageAsJson = ow.writeValueAsString(event);
        producerService.SendMessage(messageAsJson);
        return ResponseEntity.ok().build();
    }

    // Rest endpoint za upit statusa
    @GetMapping("/status/{id}")
    public ResponseEntity<Event> GetStatus(@PathVariable("id") Long id) {
        return ResponseEntity.ok(eventService.GetById(id));
    }
}
