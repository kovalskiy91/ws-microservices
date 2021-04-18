package ws.microservices.insurance.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ws.microservices.insurance.entities.OrganizationEntity;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<OrganizationEntity, Long> {
}
