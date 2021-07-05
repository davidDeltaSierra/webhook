package br.com.webhook.repository;

import br.com.webhook.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {
    List<Tag> findAllByNameIn(List<String> tags);
}
