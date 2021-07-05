package br.com.webhook.model;

import br.com.webhook.dto.request.MediaTypeDto;
import lombok.*;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "request")
public class Request extends AbstractEntity {
    @Column(name = "event_uuid", nullable = false)
    private String eventUuid;

    @Column(nullable = false)
    private String url;

    @Column
    private String headers;

    @Column
    private String query;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HttpMethod method;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MediaTypeDto contentType;

    @Column(columnDefinition = "json")
    private String body;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Set<TagAssociation> tagAssociations;

    public Set<Tag> getTags() {
        return ofNullable(tagAssociations)
                .map(it -> it.stream()
                        .map(TagAssociation::getTag)
                        .collect(Collectors.toSet()))
                .orElse(null);
    }
}
