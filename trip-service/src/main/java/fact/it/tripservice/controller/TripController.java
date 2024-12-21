package fact.it.tripservice.controller;

import fact.it.tripservice.dto.BikeResponse;
import fact.it.tripservice.dto.TripResponse;
import fact.it.tripservice.service.BikeService;
import fact.it.tripservice.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    private final TripService tripService;
    private final BikeService bikeService;

    public TripController(TripService tripService, BikeService bikeService) {
        this.tripService = tripService;
        this.bikeService = bikeService;
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

    @GetMapping("bikes")
    public List<BikeResponse> getBikes() {
        return this.bikeService.getAll();
    }
}
