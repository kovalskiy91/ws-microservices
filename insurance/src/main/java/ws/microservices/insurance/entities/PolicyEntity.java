package ws.microservices.insurance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "policy")
@SequenceGenerator(
        name = PolicyEntity.POLICY_SEQ_GEN,
        sequenceName = "policy_id_seq",
        allocationSize = 1
)
@Setter
@Getter
public class PolicyEntity {
    public static final String POLICY_SEQ_GEN = "policy_id_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = POLICY_SEQ_GEN)
    private Long id;
    @OneToOne
    @JoinColumn(name = "proposal_id")
    private ProposalEntity proposal;
    @OneToOne
    @JoinColumn(name = "plan_holder_id")
    private OrganizationEntity planHolder;
}
