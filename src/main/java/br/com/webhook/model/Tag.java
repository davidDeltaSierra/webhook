package br.com.webhook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class Tag extends AbstractEntity {
    public Tag(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    private String name;
}
