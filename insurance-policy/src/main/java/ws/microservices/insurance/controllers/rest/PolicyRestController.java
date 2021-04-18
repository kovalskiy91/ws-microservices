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
import ws.microservices.insurance.commands.CreatePolicyCommand;
import ws.microservices.insurance.models.PolicyModel;
import ws.microservices.insurance.queries.FindAllQuery;
import ws.microservices.insurance.queries.FindOneQuery;
import ws.microservices.insurance.services.PolicyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/policies")
@RequiredArgsConstructor
public class PolicyRestController {
    private final PolicyService policyService;
    private final RestResponseBuilder restResponseBuilder;

    @PostMapping
    public ResponseEntity<PolicyModel> create(@RequestBody PolicyModel policy) {
        CreatePolicyCommand command = CreatePolicyCommand.from(policy);
        PolicyModel created = policyService.process(command);
        return restResponseBuilder.buildPostResponseFrom(created);
    }

    @GetMapping
    public ResponseEntity<List<PolicyModel>> findAll(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sortBy", required = false) String... sortBy
    ) {
        FindAllQuery query = FindAllQuery.from(page, size, sortBy);
        List<PolicyModel> policies = policyService.process(query);
        return restResponseBuilder.buildGetResponseFrom(policies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyModel> findBy(@PathVariable("id") Long id) {
        FindOneQuery query = FindOneQuery.from(id);
        PolicyModel policy = policyService.process(query);
        return restResponseBuilder.buildGetResponseFrom(policy);
    }
}
