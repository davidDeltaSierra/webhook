package br.com.audit;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
public final class AuditEntityListener {
    @PrePersist
    void onPrePersist(Object o) {
        eventDispatcher(factoryAudit(o, AuditEntityEvent.PRE_PERSIST));
    }

    @PostPersist
    void onPostPersist(Object o) {
        eventDispatcher(factoryAudit(o, AuditEntityEvent.POST_PERSIST));
    }

    @PostLoad
    void onPostLoad(Object o) {
        eventDispatcher(factoryAudit(o, AuditEntityEvent.POST_LOAD));
    }

    @PreUpdate
    void onPreUpdate(Object o) {
        eventDispatcher(factoryAudit(o, AuditEntityEvent.PRE_UPDATE));
    }

    @PostUpdate
    void onPostUpdate(Object o) {
        eventDispatcher(factoryAudit(o, AuditEntityEvent.POST_UPDATE));
    }

    @PreRemove
    void onPreRemove(Object o) {
        eventDispatcher(factoryAudit(o, AuditEntityEvent.PRE_REMOVE));
    }

    @PostRemove
    void onPostRemove(Object o) {
        eventDispatcher(factoryAudit(o, AuditEntityEvent.POST_REMOVE));
    }

    private <T> void eventDispatcher(AuditEntity<T> auditEntity) {
        log.debug("Dispatcher new AuditEntity event: {}, {}", auditEntity.getEvent(), auditEntity);
        AuditEntityConfig.getInstance()
                .eventDispatcher(auditEntity);
    }

    private <T> AuditEntity<T> factoryAudit(T o, AuditEntityEvent auditEntityEvent) {
        return AuditEntity.<T>builder()
                .entity(o)
                .event(auditEntityEvent)
                .build();
    }
}