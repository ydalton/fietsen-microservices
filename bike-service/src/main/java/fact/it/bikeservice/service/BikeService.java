package fact.it.bikeservice.service;

import fact.it.bikeservice.dto.BikeResponse;
import fact.it.bikeservice.dto.BikeRequest;
import fact.it.bikeservice.model.Bike;
import fact.it.bikeservice.repository.BikeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {
    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @PostConstruct
    public void addBikes() {
        if(bikeRepository.count() > 0)
            return;
        bikeRepository.save(new Bike(2019,  "Trek", "Model S"));
        bikeRepository.save(new Bike(2020, "Specialized", "Model X"));
        bikeRepository.save(new Bike(2018, "Cannondale", "Roadster"));
        bikeRepository.save(new Bike(2021, "Giant", "Enduro"));
        bikeRepository.save(new Bike(2017, "Scott", "Mountain Pro"));
        bikeRepository.save(new Bike(2022,  "Bianchi", "Speedster"));
        bikeRepository.save(new Bike(2019,  "Merida", "Turbo"));
        bikeRepository.save(new Bike(2020, "Cube", "Trail Master"));
        bikeRepository.save(new Bike(2018, "Santa Cruz", "Adventure"));
        bikeRepository.save(new Bike(2021, "Yamaha", "Road King"));
    }

    public List<BikeResponse> getBikes() {
        List<Bike> bikes = bikeRepository.findAll();

        return bikes.stream()
                .map(this::getBikeDto)
                .toList();
    }

    public Optional<BikeResponse> getBikeById(long id) {
        Optional<Bike> bike = bikeRepository.findById(id);

        return bike.map(this::getBikeDto);
    }

    public BikeResponse addBike(BikeRequest bikeRequest) {
        Bike bike = Bike.builder()
                .year(bikeRequest.getYear())
                .manufacturer(bikeRequest.getManufacturer())
                .model(bikeRequest.getModel())
                .build();

        return getBikeDto(this.bikeRepository.save(bike));
    }

    public void updateBike(long id, BikeRequest bikeRequest) {
        Optional<Bike> bike = bikeRepository.findById(id);
        if(bike.isPresent()) {
            bike.get().setManufacturer(bikeRequest.getManufacturer());
            bike.get().setModel(bikeRequest.getModel());
            bike.get().setYear(bikeRequest.getYear());
            bikeRepository.save(bike.get());
        }
    }

    public void deleteBike(long id) {
        bikeRepository.deleteById(id);
    }

    public boolean bikeExists(long id) {
        return bikeRepository.existsById(id);
    }

    public BikeResponse getBikeDto(Bike bike) {
        return BikeResponse.builder()
                .id(bike.getId())
                .year(bike.getYear())
                .manufacturer(bike.getManufacturer())
                .model(bike.getModel())
                .build();
    }
}
