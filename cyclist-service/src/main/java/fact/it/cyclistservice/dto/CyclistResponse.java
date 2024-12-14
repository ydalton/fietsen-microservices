package fact.it.cyclistservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CyclistResponse {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
}
