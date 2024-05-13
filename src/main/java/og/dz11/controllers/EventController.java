package og.dz11.controllers;

import lombok.RequiredArgsConstructor;
import og.dz11.models.Event;
import og.dz11.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<Event>> All() {
        return ResponseEntity.ok(eventService.GetAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Event> GetById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.GetById(id));
    }

    @PostMapping()
    public ResponseEntity<Event> CreateEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.Add(event));
    }

    @PutMapping()
    public ResponseEntity<Event> UpdateEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.Update(event));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Event> DeleteEvent(@PathVariable Long id) {
        eventService.Delete(id);
        return ResponseEntity.ok().build();
    }
}
