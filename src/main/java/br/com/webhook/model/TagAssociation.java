package br.com.webhook.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tag_association")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(
                name = "TagAssociation.tag",
                attributeNodes = {
                        @NamedAttributeNode("tag")
                }
        )
})
public class TagAssociation extends AbstractEntity {
    @Column(nullable = false)
    private String identifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public Tag getTag() {
        return Hibernate.isInitialized(tag) ? tag : null;
    }
}
