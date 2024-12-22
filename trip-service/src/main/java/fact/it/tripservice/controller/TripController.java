package fact.it.tripservice.controller;

import fact.it.tripservice.dto.TripResponse;
import fact.it.tripservice.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public List<TripResponse> get() {
        return this.tripService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<TripResponse> getByTripId(@PathVariable("id") String tripId) {
        Optional<TripResponse> trip = this.tripService.getById(tripId);

        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TripResponse> deleteByTripId(@PathVariable("id") String tripId) {
        if(!tripService.existsById(tripId)) {
            return ResponseEntity.notFound().build();
        }
        this.tripService.deleteById(tripId);
        return ResponseEntity.noContent().build();
    }
}
