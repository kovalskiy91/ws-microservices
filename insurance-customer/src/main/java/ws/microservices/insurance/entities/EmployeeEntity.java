package ws.microservices.insurance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@SequenceGenerator(
        name = EmployeeEntity.EMPLOYEE_SEQ_GEN,
        sequenceName = "employee_id_seq",
        allocationSize = 1
)
@Setter
@Getter
public class EmployeeEntity {
    public static final String EMPLOYEE_SEQ_GEN = "employee_seq_gen";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = EMPLOYEE_SEQ_GEN)
    private Long id;
    @OneToOne
    @JoinColumn(name = "individual_id")
    private IndividualEntity individual;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private OrganizationEntity employer;
}
