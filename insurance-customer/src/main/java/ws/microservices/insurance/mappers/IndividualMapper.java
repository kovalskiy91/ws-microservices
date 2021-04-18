package ws.microservices.insurance.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ws.microservices.insurance.entities.IndividualEntity;
import ws.microservices.insurance.models.IndividualModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndividualMapper {

    List<IndividualModel> entitiesToModels(List<IndividualEntity> individuals);

    @Mapping(target = "id", ignore = true)
    IndividualEntity modelToEntity(IndividualModel model);

    IndividualModel entityToModel(IndividualEntity entity);
}
