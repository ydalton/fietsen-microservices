package fact.it.bikeservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String model;
    private String manufacturer;
    public Bike(int year, String model, String manufacturer) {
        this.year = year;
        this.model = model;
        this.manufacturer = manufacturer;
    }
}
