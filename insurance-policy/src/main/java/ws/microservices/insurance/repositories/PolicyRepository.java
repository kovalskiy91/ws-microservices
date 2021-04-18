package ws.microservices.insurance.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ws.microservices.insurance.entities.PolicyEntity;

@Repository
public interface PolicyRepository extends PagingAndSortingRepository<PolicyEntity, Long> {
}
