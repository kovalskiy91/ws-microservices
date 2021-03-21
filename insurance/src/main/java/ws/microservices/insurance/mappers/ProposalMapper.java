package ws.microservices.insurance.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ws.microservices.insurance.entities.ProposalEntity;
import ws.microservices.insurance.models.ProposalModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProposalMapper {

    List<ProposalModel> entitiesToModels(List<ProposalEntity> models);

    @Mapping(target = "planHolder", ignore = true)
    ProposalEntity modelToEntity(ProposalModel model);

    @Mapping(target = "planHolderId", source = "planHolder.id")
    ProposalModel entityToModel(ProposalEntity entity);
}
