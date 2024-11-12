package fact.it.bikeservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BikeDto {
    private Long id;
    private int year;
    private String name;
}
