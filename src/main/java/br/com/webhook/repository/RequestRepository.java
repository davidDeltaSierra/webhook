package br.com.webhook.repository;

import br.com.webhook.model.Request;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {

}
