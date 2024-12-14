package fact.it.cyclistservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="cyclist")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class Cyclist {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    public Cyclist(String firstname, String lastname, int age, Gender gender) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.age = age;
        this.gender = gender;
    }
}
