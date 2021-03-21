package ws.microservices.insurance.commands;

import lombok.Getter;
import lombok.Setter;
import ws.microservices.insurance.models.OrganizationModel;

@Setter
@Getter
public class CreateOrganizationCommand implements Command {
    private OrganizationModel organization;

    public static CreateOrganizationCommand from(OrganizationModel organization) {
        CreateOrganizationCommand command = new CreateOrganizationCommand();
        command.organization = organization;
        return command;
    }
}
