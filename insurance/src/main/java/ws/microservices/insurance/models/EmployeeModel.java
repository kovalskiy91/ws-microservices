package ws.microservices.insurance.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeModel implements Model {
    private Long id;
    private Long individualId;
}
