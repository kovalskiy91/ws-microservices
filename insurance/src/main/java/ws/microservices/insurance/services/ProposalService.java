package ws.microservices.insurance.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ws.microservices.insurance.commands.CreateProposalCommand;
import ws.microservices.insurance.entities.OrganizationEntity;
import ws.microservices.insurance.entities.ProposalEntity;
import ws.microservices.insurance.mappers.ProposalMapper;
import ws.microservices.insurance.models.ProposalModel;
import ws.microservices.insurance.queries.FindAllQuery;
import ws.microservices.insurance.queries.FindOneQuery;
import ws.microservices.insurance.repositories.OrganizationRepository;
import ws.microservices.insurance.repositories.PageRequestAdapter;
import ws.microservices.insurance.repositories.ProposalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProposalService {
    private final ProposalMapper proposalMapper;
    private final OrganizationRepository organizationRepository;
    private final ProposalRepository proposalRepository;

    public ProposalModel process(CreateProposalCommand command) {
        ProposalModel proposalModel = command.getProposal();
        ProposalEntity proposalEntity = proposalMapper.modelToEntity(proposalModel);

        Long planHolderId = proposalModel.getPlanHolderId();
        OrganizationEntity organizationEntity = organizationRepository
                .findById(planHolderId)
                .orElseThrow(() -> new ModelDoesNotExistException("Plan Holder", planHolderId));
        proposalEntity.setPlanHolder(organizationEntity);

        ProposalEntity savedProposalEntity = proposalRepository.save(proposalEntity);
        return proposalMapper.entityToModel(savedProposalEntity);
    }

    public List<ProposalModel> process(FindAllQuery query) {
        List<ProposalEntity> proposals = proposalRepository
                .findAll(PageRequestAdapter.from(query))
                .get()
                .collect(Collectors.toList());
        return proposalMapper.entitiesToModels(proposals);
    }

    public ProposalModel process(FindOneQuery query) {
        Long proposalId = query.getId();
        ProposalEntity proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new ModelNotFoundException("Proposal", proposalId));
        return proposalMapper.entityToModel(proposal);
    }
}
