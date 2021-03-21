package ws.microservices.insurance.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ws.microservices.insurance.commands.CreatePolicyCommand;
import ws.microservices.insurance.entities.OrganizationEntity;
import ws.microservices.insurance.entities.PolicyEntity;
import ws.microservices.insurance.entities.ProposalEntity;
import ws.microservices.insurance.mappers.PolicyMapper;
import ws.microservices.insurance.models.PolicyModel;
import ws.microservices.insurance.queries.FindAllQuery;
import ws.microservices.insurance.queries.FindOneQuery;
import ws.microservices.insurance.repositories.OrganizationRepository;
import ws.microservices.insurance.repositories.PageRequestAdapter;
import ws.microservices.insurance.repositories.PolicyRepository;
import ws.microservices.insurance.repositories.ProposalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicyService {
    private final PolicyMapper policyMapper;
    private final OrganizationRepository organizationRepository;
    private final ProposalRepository proposalRepository;
    private final PolicyRepository policyRepository;

    public PolicyModel process(CreatePolicyCommand command) {
        PolicyModel policyModel = command.getPolicy();
        PolicyEntity policyEntity = policyMapper.modelToEntity(policyModel);

        Long proposalId = policyModel.getProposalId();
        ProposalEntity proposalEntity = proposalRepository
                .findById(proposalId)
                .orElseThrow(() -> new ModelDoesNotExistException("Proposal", proposalId));
        policyEntity.setProposal(proposalEntity);

        Long planHolderId = policyModel.getPlanHolderId();
        OrganizationEntity planHolder = organizationRepository
                .findById(planHolderId)
                .orElseThrow(() -> new ModelDoesNotExistException("Plan Holder", planHolderId));
        policyEntity.setPlanHolder(planHolder);

        policyRepository.save(policyEntity);
        return policyMapper.entityToModel(policyEntity);
    }

    public List<PolicyModel> process(FindAllQuery query) {
        List<PolicyEntity> policies = policyRepository
                .findAll(PageRequestAdapter.from(query))
                .stream()
                .collect(Collectors.toList());
        return policyMapper.entitiesToModels(policies);
    }

    public PolicyModel process(FindOneQuery query) {
        Long policyId = query.getId();
        PolicyEntity policy = policyRepository
                .findById(policyId)
                .orElseThrow(() -> new ModelNotFoundException("Policy", policyId));
        return policyMapper.entityToModel(policy);
    }
}
