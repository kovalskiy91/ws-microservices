package ws.microservices.insurance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organization")
@SequenceGenerator(
        name = OrganizationEntity.ORGANIZATION_SEQ_GEN,
        sequenceName = "organization_id_seq",
        allocationSize = 1
)
@Setter
@Getter
public class OrganizationEntity implements ws.microservices.insurance.entities.Entity {
    public static final String ORGANIZATION_SEQ_GEN = "organization_seq_gen";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ORGANIZATION_SEQ_GEN)
    private Long id;
    private String title;
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees = new ArrayList<>();

    public void addEmployee(EmployeeEntity employee) {
        employees.add(employee);
        employee.setEmployer(this);
    }
}
