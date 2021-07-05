package br.com.webhook.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "response")
@NamedEntityGraph(name = "Response.full",
        attributeNodes = {
                @NamedAttributeNode(value = "request", subgraph = "Response.request")
        },
        subgraphs = {
                @NamedSubgraph(name = "Response.request",
                        attributeNodes = {
                                @NamedAttributeNode(value = "tagAssociations", subgraph = "Response.request.tagAssociations")
                        }),
                @NamedSubgraph(name = "Response.request.tagAssociations",
                        attributeNodes = {
                                @NamedAttributeNode(value = "tag")
                        })
        }
)
public class Response extends AbstractEntity {
    @Column(name = "status_code", nullable = false)
    private Integer statusCode;

    @Column(length = 2048)
    private String response;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;

    public Request getRequest() {
        return Hibernate.isInitialized(request) ? request : null;
    }
}
