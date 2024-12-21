package fact.it.tripservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("trip")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    private String id;
    private Location startLocation;
    private Location endLocation;
    private Date startTime;
    private Date endTime;
    @Getter(AccessLevel.NONE)
    private String cyclistId;
    @Getter(AccessLevel.NONE)
    private long bikeId;
}