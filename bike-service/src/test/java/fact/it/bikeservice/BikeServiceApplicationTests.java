package fact.it.bikeservice;

import fact.it.bikeservice.dto.BikeRequest;
import fact.it.bikeservice.dto.BikeResponse;
import fact.it.bikeservice.repository.BikeRepository;
import fact.it.bikeservice.service.BikeService;
import fact.it.bikeservice.model.Bike;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BikeServiceApplicationTests {
	@InjectMocks
	private BikeService bikeService;
	@Mock
	private BikeRepository bikeRepository;

	@Test
	public void testWhetherBikeIsCreated() {
		/* arrange */
		BikeRequest bikeRequest = new BikeRequest();

		bikeRequest.setYear(2020);
		bikeRequest.setManufacturer("Kona");
		bikeRequest.setModel("Sutra SE");

		when(bikeRepository.save(any(Bike.class))).thenReturn(new Bike());

		/* act */
		bikeService.addBike(bikeRequest);

		verify(bikeRepository, times(1)).save(any(Bike.class));
	}

	@Test
	public void testWhetherBikeIsFound() {
		/* arrange */
		Bike bike = new Bike();
		bike.setYear(2020);
		bike.setManufacturer("Kona");
		bike.setModel("Sutra SE");
		bike.setId(1L);

		when(bikeRepository.findById(1L)).thenReturn(Optional.of(bike));

		/* act */
		Optional<BikeResponse> returnedBike = bikeService.getBikeById(1L);

		/* assert */
		assertTrue(returnedBike.isPresent());
		assertEquals("Kona", returnedBike.get().getManufacturer());
		assertEquals("Sutra SE", returnedBike.get().getModel());
		assertEquals(2020, returnedBike.get().getYear());
	}


	@Test
	public void testWhetherBikeIsNotFound() {
		/* arrange */
		when(bikeRepository.findById(1L)).thenReturn(Optional.empty());

		/* act */
		Optional<BikeResponse> returnedBike = bikeService.getBikeById(1L);

		/* assert */
		assertTrue(returnedBike.isEmpty());
	}


	@Test
	public void testWhetherBikeExists() {
		/* arrange */
		BikeRequest bike = new BikeRequest();
		bike.setYear(2020);
		bike.setManufacturer("Kona");
		bike.setModel("Sutra SE");

		when(bikeRepository.save(any(Bike.class))).thenReturn(new Bike());
		when(bikeRepository.findById(1L)).thenReturn(Optional.of(new Bike()));
		when(bikeRepository.existsById(1L)).thenReturn(true);

		/* act */
		bikeService.addBike(bike);

		/* assert  */
		assertTrue(bikeService.bikeExists(1L));
		assertTrue(bikeService.getBikeById(1L).isPresent());
		verify(bikeRepository, times(1)).save(any(Bike.class));
	}

	@Test
	public void testWhetherBikeIsDeleted() {
		Bike bike = new Bike();
		bike.setYear(2020);
		bike.setManufacturer("Kona");
		bike.setModel("Sutra SE");
		bike.setId(1L);

		when(bikeRepository.save(any(Bike.class))).thenReturn(bike);
		bikeRepository.save(bike);
		when(bikeRepository.existsById(1L)).thenReturn(true);

		assertEquals(1, bike.getId());
		bikeService.deleteBike(1L);
		when(bikeRepository.existsById(1L)).thenReturn(false);

		assertFalse(bikeService.bikeExists(1L));
		verify(bikeRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testWhetherBikeIsUpdated() {
		Bike bike = new Bike();
		bike.setYear(2020);
		bike.setManufacturer("Kona");
		bike.setModel("Sutra SE");
		bike.setId(1L);

		when(bikeRepository.save(any(Bike.class))).thenAnswer(invocation -> {
			Bike bike1 = invocation.getArgument(0);
			bike1.setId(1L);
			return bike1;
		});

		BikeRequest bikeRequest = BikeRequest.builder()
									.year(2020)
									.manufacturer("Kona")
									.model("Sutra SE")
									.build();

		BikeResponse response = bikeService.addBike(bikeRequest);

		assertEquals(1L, response.getId());
		bikeRequest.setYear(2024);


		when(bikeRepository.findById(1L)).thenReturn(Optional.of(bike));
		bikeService.updateBike(1L, bikeRequest);

		verify(bikeRepository, times(2)).save(any(Bike.class));
	}

	@Test
	public void testWhetherAllBikesWereFetched() {
		when(bikeRepository.findAll()).thenReturn(new ArrayList<>());

		List<BikeResponse> bikes = bikeService.getBikes();

		assertEquals(0, bikes.size());

		verify(bikeRepository, times(1)).findAll();
	}

	@Test
	public void testWhetherDataWasAdded() {
		when(bikeRepository.count()).thenReturn(0L);
		bikeService.addBikes();
		verify(bikeRepository, times(1)).count();
	}

	@Test
	public void testBikeWasNotUpdated() {
		when(bikeRepository.findById(1L)).thenReturn(Optional.empty());

		BikeRequest bike = BikeRequest.builder()
				.model("Sutra SE")
				.manufacturer("Kona")
				.year(2020)
				.build();

		bikeService.updateBike(1, bike);

		verify(bikeRepository, never()).save(any(Bike.class));
	}
}
