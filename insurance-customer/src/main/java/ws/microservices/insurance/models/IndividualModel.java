package ws.microservices.insurance.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IndividualModel implements Model {
    private Long id;
    private String firstName;
    private String lastName;
}
