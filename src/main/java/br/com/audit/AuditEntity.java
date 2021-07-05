package br.com.audit;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class AuditEntity<T> {
    @Builder.Default
    LocalDateTime date = LocalDateTime.now();
    AuditEntityEvent event;
    T entity;
}
