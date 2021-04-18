package ws.microservices.insurance.commands;

import lombok.Getter;
import lombok.Setter;
import ws.microservices.insurance.models.ProposalModel;

@Setter
@Getter
public class CreateProposalCommand {
    private ProposalModel proposal;

    public static CreateProposalCommand from(ProposalModel proposal) {
        CreateProposalCommand command = new CreateProposalCommand();
        command.proposal = proposal;
        return command;
    }
}
