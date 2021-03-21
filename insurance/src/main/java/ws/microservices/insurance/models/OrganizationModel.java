package ws.microservices.insurance.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class OrganizationModel implements Model {
    private Long id;
    private String title;
    private Set<EmployeeModel> employees;
}
