package ws.microservices.insurance.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ws.microservices.insurance.entities.IndividualEntity;

@Repository
public interface IndividualRepository extends PagingAndSortingRepository<IndividualEntity, Long> {
}
