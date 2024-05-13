package og.dz11.controllers;

import lombok.RequiredArgsConstructor;
import og.dz11.models.City;
import og.dz11.services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping("/all")
    public ResponseEntity<List<City>> All() {
        return ResponseEntity.ok(cityService.GetAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> GetById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.GetById(id));
    }

    @PostMapping()
    public ResponseEntity<City> CreateCity(@RequestBody City city) {
        return ResponseEntity.ok(cityService.Add(city));
    }

    @PutMapping()
    public ResponseEntity<City> UpdateCity(@RequestBody City city) {

        return ResponseEntity.ok(cityService.Update(city));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> DeleteCity(@PathVariable Long id) {
        cityService.Delete(id);
        return ResponseEntity.ok().build();
    }
}