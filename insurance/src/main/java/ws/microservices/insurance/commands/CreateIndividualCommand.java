package ws.microservices.insurance.commands;

import lombok.Getter;
import lombok.Setter;
import ws.microservices.insurance.models.IndividualModel;

@Setter
@Getter
public class CreateIndividualCommand implements Command {
    private IndividualModel individual;

    public static CreateIndividualCommand from(IndividualModel individual) {
        CreateIndividualCommand command = new CreateIndividualCommand();
        command.individual = individual;
        return command;
    }
}
