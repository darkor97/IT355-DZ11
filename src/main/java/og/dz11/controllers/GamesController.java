package og.dz11.controllers;

import lombok.RequiredArgsConstructor;
import og.dz11.models.Games;
import og.dz11.services.GamesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GamesController {
    private final GamesService gamesService;

    @GetMapping("/all")
    public ResponseEntity<List<Games>> All() {
        return ResponseEntity.ok(gamesService.GetAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Games> GetById(@PathVariable Long id) {
        return ResponseEntity.ok(gamesService.GetById(id));
    }

    @PostMapping()
    public ResponseEntity<Games> CreateGames(@RequestBody Games games) {
        return ResponseEntity.ok(gamesService.Add(games));
    }

    @PutMapping()
    public ResponseEntity<Games> UpdateGames(@RequestBody Games games) {
        return ResponseEntity.ok(gamesService.Update(games));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Games> DeletGames(@PathVariable Long id) {
        gamesService.Delete(id);
        return ResponseEntity.ok().build();
    }
}
