package ws.microservices.insurance.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ws.microservices.insurance.entities.PolicyEntity;
import ws.microservices.insurance.models.PolicyModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PolicyMapper {
    List<PolicyModel> entitiesToModels(List<PolicyEntity> models);

    @Mapping(target = "proposal", ignore = true)
    @Mapping(target = "planHolder", ignore = true)
    PolicyEntity modelToEntity(PolicyModel model);

    @Mapping(target = "proposalId", source = "proposal.id")
    @Mapping(target = "planHolderId", source = "planHolder.id")
    PolicyModel entityToModel(PolicyEntity entity);
}
