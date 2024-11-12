package fact.it.bikeservice.controller;

import fact.it.bikeservice.dto.BikeDto;
import fact.it.bikeservice.service.BikeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bike")
public class BikeController {
    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }
    @GetMapping("")
    public List<BikeDto> getBikes() {
        List<BikeDto> bikeDtos = bikeService.getBikes();

        return bikeDtos;
    }
}
