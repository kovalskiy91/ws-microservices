package ws.microservices.insurance.mappers;

import org.mapstruct.Mapper;
import ws.microservices.insurance.entities.ProposalEntity;
import ws.microservices.insurance.models.ProposalModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProposalMapper {

    List<ProposalModel> entitiesToModels(List<ProposalEntity> models);

    ProposalEntity modelToEntity(ProposalModel model);

    ProposalModel entityToModel(ProposalEntity entity);
}
