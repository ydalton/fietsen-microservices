package fact.it.tripservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fact.it.tripservice.model.Location;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {
    private String id;
    private Location startLocation;
    private Location endLocation;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @Getter(AccessLevel.NONE)
    private String cyclistId;
    @Getter(AccessLevel.NONE)
    private long bikeId;
}
