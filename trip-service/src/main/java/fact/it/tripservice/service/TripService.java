package fact.it.tripservice.service;

import fact.it.tripservice.dto.TripResponse;
import fact.it.tripservice.model.Location;
import fact.it.tripservice.model.Trip;
import fact.it.tripservice.repository.TripRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @PostConstruct
    public void init() {
        if(this.tripRepository.count() > 0)
            return;

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(37.7749f, -122.4194f))
                        .endLocation(new Location(34.0522f, -118.2437f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 3600000)) // 1 hour later
                        .cyclistId("cyclist1")
                        .bikeId(101L)
                        .build()
        );

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(40.7128f, -74.0060f))
                        .endLocation(new Location(42.3601f, -71.0589f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 7200000)) // 2 hours later
                        .cyclistId("cyclist2")
                        .bikeId(102L)
                        .build()
        );

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(41.8781f, -87.6298f))
                        .endLocation(new Location(38.9072f, -77.0369f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 5400000)) // 1.5 hours later
                        .cyclistId("cyclist3")
                        .bikeId(103L)
                        .build()
        );

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(47.6062f, -122.3321f))
                        .endLocation(new Location(37.7749f, -122.4194f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 3600000)) // 1 hour later
                        .cyclistId("cyclist4")
                        .bikeId(104L)
                        .build()
        );

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(29.7604f, -95.3698f))
                        .endLocation(new Location(30.2672f, -97.7431f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 4500000)) // 1.25 hours later
                        .cyclistId("cyclist5")
                        .bikeId(105L)
                        .build()
        );

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(39.7392f, -104.9903f))
                        .endLocation(new Location(40.0149f, -105.2705f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 3000000)) // 50 minutes later
                        .cyclistId("cyclist6")
                        .bikeId(106L)
                        .build()
        );

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(25.7617f, -80.1918f))
                        .endLocation(new Location(27.9944f, -82.5944f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 5400000)) // 1.5 hours later
                        .cyclistId("cyclist7")
                        .bikeId(107L)
                        .build()
        );

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(33.4484f, -112.0740f))
                        .endLocation(new Location(36.1699f, -115.1398f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 10800000)) // 3 hours later
                        .cyclistId("cyclist8")
                        .bikeId(108L)
                        .build()
        );

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(32.7767f, -96.7970f))
                        .endLocation(new Location(35.2271f, -80.8431f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 14400000)) // 4 hours later
                        .cyclistId("cyclist9")
                        .bikeId(109L)
                        .build()
        );

        this.tripRepository.save(
                Trip.builder()
                        .id(null)
                        .startLocation(new Location(39.7684f, -86.1581f))
                        .endLocation(new Location(40.4173f, -82.9071f))
                        .startTime(new Date())
                        .endTime(new Date(System.currentTimeMillis() + 2700000)) // 45 minutes later
                        .cyclistId("cyclist10")
                        .bikeId(110L)
                        .build()
        );
    }

    public List<TripResponse> getAll() {
        return this.tripRepository.findAll()
                                  .stream()
                                  .map(this::mapToTripResponse)
                                  .toList();
    }

    public Optional<TripResponse> getById(String id) {
        Optional<Trip> cyclist = tripRepository.findById(id);

        return cyclist.map(this::mapToTripResponse);
    }

    private TripResponse mapToTripResponse(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .startLocation(trip.getStartLocation())
                .endLocation(trip.getEndLocation())
                .startTime(trip.getStartTime())
                .endTime(trip.getEndTime())
                .build();
    }
}
