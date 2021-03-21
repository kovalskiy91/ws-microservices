package ws.microservices.insurance.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProposalModel implements Model {
    private Long id;
    private Long planHolderId;
}
