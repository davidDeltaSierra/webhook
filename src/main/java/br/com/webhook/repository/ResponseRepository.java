package br.com.webhook.repository;

import br.com.webhook.model.Response;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResponseRepository extends CrudRepository<Response, Long>, EntityGraphJpaSpecificationExecutor<Response> {
    Optional<Response> findByRequest_EventUuid(String uuid);
}
