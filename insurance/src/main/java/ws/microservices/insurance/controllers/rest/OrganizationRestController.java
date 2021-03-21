package ws.microservices.insurance.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.microservices.insurance.commands.CreateOrganizationCommand;
import ws.microservices.insurance.models.OrganizationModel;
import ws.microservices.insurance.queries.FindAllQuery;
import ws.microservices.insurance.queries.FindOneQuery;
import ws.microservices.insurance.services.OrganizationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationRestController {
    private final OrganizationService organizationService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping
    public ResponseEntity<OrganizationModel> create(@RequestBody OrganizationModel organization) {
        CreateOrganizationCommand command = CreateOrganizationCommand.from(organization);
        OrganizationModel createdOrganization = organizationService.process(command);
        return restResponseBuilder.buildPostResponseFrom(createdOrganization);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationModel>> findAll(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sortBy", required = false) String... sortBy
    ) {
        FindAllQuery query = FindAllQuery.from(page, size, sortBy);
        List<OrganizationModel> individuals = organizationService.process(query);
        return restResponseBuilder.buildGetResponseFrom(individuals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationModel> findBy(@PathVariable("id") Long id) {
        FindOneQuery query = FindOneQuery.from(id);
        OrganizationModel organization = organizationService.process(query);
        return restResponseBuilder.buildGetResponseFrom(organization);
    }
}
