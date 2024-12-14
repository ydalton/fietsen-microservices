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
	public void testBikeConstructor() {
		Bike bike = new Bike(2020, "Kona", "Sutra SE");

		assertEquals(2020, bike.getYear());
		assertEquals("Kona", bike.getManufacturer());
		assertEquals("Sutra SE", bike.getModel());
	}
}
