package br.com.webhook.repository;

import br.com.webhook.model.TagAssociation;
import org.springframework.data.repository.CrudRepository;

public interface TagAssociationRepository extends CrudRepository<TagAssociation, Long> {
    void deleteByIdentifier(String identifier);
}
