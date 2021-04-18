package ws.microservices.insurance.mappers;

import org.mapstruct.Mapper;
import ws.microservices.insurance.entities.PolicyEntity;
import ws.microservices.insurance.models.PolicyModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PolicyMapper {
    List<PolicyModel> entitiesToModels(List<PolicyEntity> models);

    PolicyEntity modelToEntity(PolicyModel model);

    PolicyModel entityToModel(PolicyEntity entity);
}
