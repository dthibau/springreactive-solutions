package org.formation.model;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OperationRepository extends ReactiveCrudRepository<Operation, Long> {

    Flux<Operation> findByAccountId(Long accountId);
}
