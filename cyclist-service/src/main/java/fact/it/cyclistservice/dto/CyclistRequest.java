package fact.it.cyclistservice.dto;

import fact.it.cyclistservice.model.Gender;
import lombok.*;

@Getter
@Setter
public class CyclistRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
}
