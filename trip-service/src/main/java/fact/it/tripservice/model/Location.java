package fact.it.tripservice.model;

import lombok.Getter;

@Getter
public class Location {
    private float latitude;
    private float longitude;

    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
