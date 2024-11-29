package fact.it.bikeservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BikeRequest {
    private int year;
    private String model;
    private String manufacturer;
}
