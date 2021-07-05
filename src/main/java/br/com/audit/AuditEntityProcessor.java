package br.com.audit;

import com.fasterxml.jackson.core.type.TypeReference;

public abstract class AuditEntityProcessor<T> extends TypeReference<T> {
    private final Class<T> genericClass;

    @SuppressWarnings("unchecked")
    public AuditEntityProcessor() {
        this.genericClass = (Class<T>) super.getType();
    }

    @SuppressWarnings("unchecked")
    void eventProcessor(AuditEntity<?> auditEntity) {
        process((AuditEntity<T>) auditEntity);
    }

    boolean acceptHandler(AuditEntity<?> auditEntity) {
        return genericClass.isInstance(auditEntity.getEntity())
                && accept(auditEntity.getEvent());
    }

    public abstract boolean accept(AuditEntityEvent auditEntity);

    public abstract void process(AuditEntity<T> auditEntity);
}