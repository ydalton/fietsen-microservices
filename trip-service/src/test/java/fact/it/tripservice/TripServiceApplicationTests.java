package fact.it.tripservice;

import fact.it.tripservice.dto.TripResponse;
import fact.it.tripservice.model.Location;
import fact.it.tripservice.model.Trip;
import fact.it.tripservice.repository.TripRepository;
import fact.it.tripservice.service.TripService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TripServiceApplicationTests {
    @InjectMocks
    private TripService tripService;

    @Mock
    private TripRepository tripRepository;

    @Test
    public void testWhetherTripsAreAdded() {
        when(tripRepository.count()).thenReturn(0L);
        tripService.init();
        verify(tripRepository, times(10)).save(any(Trip.class));
        reset(tripRepository);
        when(tripRepository.count()).thenReturn(10L);
        tripService.init();
        verify(tripRepository, never()).save(any(Trip.class));
    }

    @Test
    public void testWhetherTripsAreFetched() {
        when(tripRepository.findAll()).thenReturn(new ArrayList<>());

        List<TripResponse> response = tripService.getAll();

        assertEquals(0, response.size());
    }

    @Test
    public void testDtoMapper() {
        Trip trip = Trip.builder()
                .id("deadbeef")
                .startLocation(new Location(0.5f, 0.1f))
                .endLocation(new Location(0, 0))
                .startTime(new Date())
                .endTime(new Date())
                .build();

        when(tripRepository.findById("deadbeef")).thenReturn(Optional.of(trip));

        Optional<TripResponse> response = tripService.getById("deadbeef");

        assertTrue(response.isPresent());
        assertEquals("deadbeef", response.get().getId());
        assertEquals(0.5f, response.get().getStartLocation().getLatitude());
        assertEquals(0.1f, response.get().getStartLocation().getLongitude());
        assertEquals(0f, response.get().getEndLocation().getLatitude());
        assertEquals(0f, response.get().getEndLocation().getLongitude());
    }

    @Test
    public void testWhetherTripWasDeleted() {
        tripService.deleteById("deadbeef");

        verify(tripRepository, times(1)).deleteById("deadbeef");
    }

    @Test
    public void testWhetherTripExists() {
        when(tripRepository.existsById("deadbeef")).thenReturn(false);

        assertFalse(tripService.existsById("deadbeef"));

        verify(tripRepository, times(1)).existsById("deadbeef");
    }
}
