package ws.microservices.insurance.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ws.microservices.insurance.commands.CreateIndividualCommand;
import ws.microservices.insurance.entities.IndividualEntity;
import ws.microservices.insurance.mappers.IndividualMapper;
import ws.microservices.insurance.models.IndividualModel;
import ws.microservices.insurance.queries.FindAllQuery;
import ws.microservices.insurance.queries.FindOneQuery;
import ws.microservices.insurance.repositories.IndividualRepository;
import ws.microservices.insurance.repositories.PageRequestAdapter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndividualService {
    private final IndividualMapper individualMapper;
    private final IndividualRepository individualRepository;

    public IndividualModel process(CreateIndividualCommand command) {
        IndividualModel model = command.getIndividual();
        IndividualEntity newEntity = individualMapper.modelToEntity(model);
        IndividualEntity savedEntity = individualRepository.save(newEntity);
        return individualMapper.entityToModel(savedEntity);
    }

    public List<IndividualModel> process(FindAllQuery query) {
        List<IndividualEntity> individuals = individualRepository
                .findAll(PageRequestAdapter.from(query))
                .get()
                .collect(Collectors.toList());
        return individualMapper.entitiesToModels(individuals);
    }

    public IndividualModel process(FindOneQuery query) {
        Long individualId = query.getId();
        IndividualEntity individual = individualRepository
                .findById(individualId)
                .orElseThrow(() -> new ModelNotFoundException("Individual", individualId));
        return individualMapper.entityToModel(individual);
    }
}
