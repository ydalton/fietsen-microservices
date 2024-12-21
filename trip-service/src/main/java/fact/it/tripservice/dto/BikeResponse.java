package fact.it.tripservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BikeResponse {
    private Long id;
    private int year;
    private String model;
    private String manufacturer;
}
