package ws.microservices.insurance.commands;

import lombok.Getter;
import lombok.Setter;
import ws.microservices.insurance.models.PolicyModel;

@Setter
@Getter
public class CreatePolicyCommand {
    private PolicyModel policy;

    public static CreatePolicyCommand from(PolicyModel policy) {
        CreatePolicyCommand command = new CreatePolicyCommand();
        command.policy = policy;
        return command;
    }
}
