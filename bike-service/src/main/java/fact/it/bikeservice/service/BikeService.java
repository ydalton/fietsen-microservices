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
    private void addBikes() {
        if(bikeRepository.count() > 0)
            return;
        bikeRepository.save(new Bike(2019, "Model S", "Trek"));
        bikeRepository.save(new Bike(2020, "Model X", "Specialized"));
        bikeRepository.save(new Bike(2018, "Roadster", "Cannondale"));
        bikeRepository.save(new Bike(2021, "Enduro", "Giant"));
        bikeRepository.save(new Bike(2017, "Mountain Pro", "Scott"));
        bikeRepository.save(new Bike(2022, "Speedster", "Bianchi"));
        bikeRepository.save(new Bike(2019, "Turbo", "Merida"));
        bikeRepository.save(new Bike(2020, "Trail Master", "Cube"));
        bikeRepository.save(new Bike(2018, "Adventure", "Santa Cruz"));
        bikeRepository.save(new Bike(2021, "Road King", "Yamaha"));
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

    private BikeResponse getBikeDto(Bike bike) {
        return BikeResponse.builder()
                .id(bike.getId())
                .year(bike.getYear())
                .manufacturer(bike.getManufacturer())
                .model(bike.getModel())
                .build();
    }
}
