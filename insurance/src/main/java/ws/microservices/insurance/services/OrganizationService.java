package ws.microservices.insurance.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ws.microservices.insurance.commands.CreateOrganizationCommand;
import ws.microservices.insurance.entities.EmployeeEntity;
import ws.microservices.insurance.entities.IndividualEntity;
import ws.microservices.insurance.entities.OrganizationEntity;
import ws.microservices.insurance.mappers.OrganizationMapper;
import ws.microservices.insurance.models.EmployeeModel;
import ws.microservices.insurance.models.OrganizationModel;
import ws.microservices.insurance.queries.FindAllQuery;
import ws.microservices.insurance.queries.FindOneQuery;
import ws.microservices.insurance.repositories.IndividualRepository;
import ws.microservices.insurance.repositories.OrganizationRepository;
import ws.microservices.insurance.repositories.PageRequestAdapter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationMapper organizationMapper;
    private final IndividualRepository individualRepository;
    private final OrganizationRepository organizationRepository;

    public OrganizationModel process(CreateOrganizationCommand command) {
        OrganizationModel organizationModel = command.getOrganization();
        OrganizationEntity organizationEntity = organizationMapper.modelToEntity(organizationModel);

        addEmployees(organizationModel.getEmployees(), organizationEntity);

        OrganizationEntity savedOrganizationEntity = organizationRepository.save(organizationEntity);
        return organizationMapper.entityToModel(savedOrganizationEntity);
    }

    private void addEmployees(Set<EmployeeModel> employeeModels, OrganizationEntity organizationEntity) {
        if (employeeModels != null) {
            employeeModels.forEach(employeeModel -> addEmployee(employeeModel, organizationEntity));
        }
    }

    private void addEmployee(EmployeeModel employeeModel, OrganizationEntity organizationEntity) {
        EmployeeEntity employeeEntity = organizationMapper.modelToEntity(employeeModel);
        Long individualId = employeeModel.getIndividualId();
        IndividualEntity individual = individualRepository
                .findById(individualId)
                .orElseThrow(() -> new ModelDoesNotExistException("Individual", individualId));
        employeeEntity.setIndividual(individual);
        organizationEntity.addEmployee(employeeEntity);
    }

    public List<OrganizationModel> process(FindAllQuery query) {
        List<OrganizationEntity> individuals = organizationRepository
                .findAll(PageRequestAdapter.from(query))
                .get()
                .collect(Collectors.toList());
        return organizationMapper.entitiesToModels(individuals);
    }

    public OrganizationModel process(FindOneQuery query) {
        Long organizationId = query.getId();
        OrganizationEntity organization = organizationRepository
                .findById(organizationId)
                .orElseThrow(() -> new ModelNotFoundException("Organization", organizationId));
        return organizationMapper.entityToModel(organization);
    }
}
