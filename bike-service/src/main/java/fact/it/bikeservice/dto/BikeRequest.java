package fact.it.bikeservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BikeRequest {
    private int year;
    private String model;
    private String manufacturer;
}
