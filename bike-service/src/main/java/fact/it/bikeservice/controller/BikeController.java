package fact.it.bikeservice.controller;

import fact.it.bikeservice.dto.BikeResponse;
import fact.it.bikeservice.dto.BikeRequest;
import fact.it.bikeservice.service.BikeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bike")
public class BikeController {
    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public List<BikeResponse> getBikes() {
        return bikeService.getBikes();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BikeResponse> getBikeById(@PathVariable Long id) {
        Optional<BikeResponse> bikeDtoOptional = this.bikeService.getBikeById(id);

        return bikeDtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BikeResponse> createBike(@RequestBody BikeRequest bikeRequest, HttpServletRequest request) {
        BikeResponse bikeResponse = this.bikeService.addBike(bikeRequest);
        String host = request.getHeader("Host");
        /* FIXME: hardcoded protocol? meh, we'll never use https anyways */
        URI location = URI.create("http://%s/api/bike/%d".formatted(host, bikeResponse.getId()));

        return ResponseEntity.created(location).body(bikeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BikeResponse> deleteBike(@PathVariable Long id) {
        if(!bikeService.bikeExists(id))
            return ResponseEntity.notFound().build();

        this.bikeService.deleteBike(id);

        return ResponseEntity.noContent().build();
    }
}
