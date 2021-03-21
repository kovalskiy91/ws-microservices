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
import ws.microservices.insurance.commands.CreateIndividualCommand;
import ws.microservices.insurance.models.IndividualModel;
import ws.microservices.insurance.queries.FindAllQuery;
import ws.microservices.insurance.queries.FindOneQuery;
import ws.microservices.insurance.services.IndividualService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/individuals")
@RequiredArgsConstructor
public class IndividualRestController {
    private final IndividualService individualService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping
    public ResponseEntity<IndividualModel> create(@RequestBody IndividualModel individual) {
        CreateIndividualCommand command = CreateIndividualCommand.from(individual);
        IndividualModel createdIndividual = individualService.process(command);
        return restResponseBuilder.buildPostResponseFrom(createdIndividual);
    }

    @GetMapping
    public ResponseEntity<List<IndividualModel>> findAll(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sortBy", required = false) String... sortBy
    ) {
        FindAllQuery query = FindAllQuery.from(page, size, sortBy);
        List<IndividualModel> individuals = individualService.process(query);
        return restResponseBuilder.buildGetResponseFrom(individuals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndividualModel> findBy(@PathVariable("id") Long id) {
        FindOneQuery query = FindOneQuery.from(id);
        IndividualModel individual = individualService.process(query);
        return restResponseBuilder.buildGetResponseFrom(individual);
    }
}
