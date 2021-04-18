package ws.microservices.insurance.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ws.microservices.insurance.entities.ProposalEntity;

@Repository
public interface ProposalRepository extends PagingAndSortingRepository<ProposalEntity, Long> {
}
