package br.com.webhook.service;

import br.com.webhook.model.AbstractEntity;
import br.com.webhook.model.Tag;
import br.com.webhook.model.TagAssociation;
import br.com.webhook.repository.TagAssociationRepository;
import br.com.webhook.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {
    private final TagAssociationRepository tagAssociationRepository;
    private final TagRepository tagRepository;

    public List<Tag> findAll() {
        return StreamSupport.stream(
                tagRepository.findAll().spliterator(),
                false
        ).collect(Collectors.toList());
    }

    public <T extends AbstractEntity> void associationWithTag(List<String> tags, T entity) {
        log.info("Associate entity: {}, in tags: {}", entity, tags);
        List<Tag> tagList = factoryTags(tags);
        List<TagAssociation> tagAssociations = tagList.stream()
                .map(tag -> factoryTagAssociation(entity, tag))
                .collect(Collectors.toList());
        tagAssociationRepository.saveAll(tagAssociations);
    }

    public void deleteByIdentifier(String identifier) {
        tagAssociationRepository.deleteByIdentifier(identifier);
    }

    private <T extends AbstractEntity> TagAssociation factoryTagAssociation(T entity, Tag tag) {
        return TagAssociation.builder()
                .tag(tag)
                .identifier(entity.getId().toString())
                .build();
    }

    private List<Tag> factoryTags(List<String> tags) {
        List<Tag> tagsInDatabase = tagRepository.findAllByNameIn(tags);
        List<Tag> newTags = saveAll(tags.stream()
                .filter(it -> tagsInDatabase.stream().noneMatch(td -> td.getName().equals(it)))
                .collect(Collectors.toList()));
        tagsInDatabase.addAll(newTags);
        return tagsInDatabase;
    }

    private List<Tag> saveAll(List<String> tags) {
        Iterable<Tag> tagsList = tagRepository.saveAll(
                tags.stream()
                        .map(Tag::new)
                        .collect(Collectors.toList())
        );
        return StreamSupport
                .stream(tagsList.spliterator(), false)
                .collect(Collectors.toList());
    }
}
