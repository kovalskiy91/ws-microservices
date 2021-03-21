package ws.microservices.insurance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "individual")
@SequenceGenerator(
        name = IndividualEntity.INDIVIDUAL_SEQ_GEN,
        sequenceName = "individual_id_seq",
        allocationSize = 1
)
@Setter
@Getter
public class IndividualEntity implements ws.microservices.insurance.entities.Entity {
    public static final String INDIVIDUAL_SEQ_GEN = "individual_seq_gen";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = INDIVIDUAL_SEQ_GEN)
    private Long id;
    private String firstName;
    private String lastName;
}
