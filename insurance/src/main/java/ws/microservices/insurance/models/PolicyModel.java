package ws.microservices.insurance.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PolicyModel implements Model {
    private Long id;
    private Long proposalId;
    private Long planHolderId;
}
