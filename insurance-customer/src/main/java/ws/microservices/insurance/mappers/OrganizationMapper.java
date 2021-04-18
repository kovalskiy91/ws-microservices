package ws.microservices.insurance.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ws.microservices.insurance.entities.EmployeeEntity;
import ws.microservices.insurance.entities.OrganizationEntity;
import ws.microservices.insurance.models.EmployeeModel;
import ws.microservices.insurance.models.OrganizationModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    List<OrganizationModel> entitiesToModels(List<OrganizationEntity> entities);

    @Mapping(target = "employees", ignore = true)
    OrganizationEntity modelToEntity(OrganizationModel model);

    OrganizationModel entityToModel(OrganizationEntity entity);

    @Mapping(target = "individual", ignore = true)
    @Mapping(target = "employer", ignore = true)
    EmployeeEntity modelToEntity(EmployeeModel model);

    @Mapping(target = "individualId", source = "individual.id")
    EmployeeModel entityToModel(EmployeeEntity entity);
}
