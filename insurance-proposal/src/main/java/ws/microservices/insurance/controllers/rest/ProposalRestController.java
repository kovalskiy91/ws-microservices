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
import ws.microservices.insurance.commands.CreateProposalCommand;
import ws.microservices.insurance.models.ProposalModel;
import ws.microservices.insurance.queries.FindAllQuery;
import ws.microservices.insurance.queries.FindOneQuery;
import ws.microservices.insurance.services.ProposalService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proposals")
@RequiredArgsConstructor
public class ProposalRestController {
    private final ProposalService proposalService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping
    public ResponseEntity<ProposalModel> create(@RequestBody ProposalModel proposal) {
        CreateProposalCommand command = CreateProposalCommand.from(proposal);
        ProposalModel created = proposalService.process(command);
        return restResponseBuilder.buildPostResponseFrom(created);
    }

    @GetMapping
    public ResponseEntity<List<ProposalModel>> findAll(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sortBy", required = false) String... sortBy
    ) {
        FindAllQuery query = FindAllQuery.from(page, size, sortBy);
        List<ProposalModel> proposals = proposalService.process(query);
        return restResponseBuilder.buildGetResponseFrom(proposals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalModel> findBy(@PathVariable("id") Long id) {
        FindOneQuery query = FindOneQuery.from(id);
        ProposalModel proposal = proposalService.process(query);
        return restResponseBuilder.buildGetResponseFrom(proposal);
    }
}
