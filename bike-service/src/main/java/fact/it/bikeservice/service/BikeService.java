package fact.it.bikeservice.service;

import fact.it.bikeservice.dto.BikeDto;
import fact.it.bikeservice.model.Bike;
import fact.it.bikeservice.repository.BikeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeService {
    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @PostConstruct
    public void addBikes()
    {
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

    public List<BikeDto> getBikes()
    {
        List<Bike> bikes = bikeRepository.findAll();

        return bikes.stream()
                .map(bike -> getBikeDto(bike))
                .toList();
    }

    private BikeDto getBikeDto(Bike bike)
    {
        BikeDto bikeDto = new BikeDto();

        bikeDto.setId(bike.getId());
        bikeDto.setYear(bike.getYear());
        bikeDto.setName(String.format("%s %s",
                                      bike.getManufacturer(),
                                      bike.getModel()));

        return bikeDto;
    }
}
