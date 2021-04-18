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
@Table(name = "proposal")
@SequenceGenerator(
        name = ProposalEntity.PROPOSAL_SEQ_GEN,
        sequenceName = "proposal_id_seq",
        allocationSize = 1
)
@Setter
@Getter
public class ProposalEntity {
    public static final String PROPOSAL_SEQ_GEN = "proposal_id_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PROPOSAL_SEQ_GEN)
    private Long id;
    private Long planHolderId;
}
