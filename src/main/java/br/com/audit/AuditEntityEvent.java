package br.com.audit;

public enum AuditEntityEvent {
    PRE_PERSIST,
    POST_PERSIST,
    POST_LOAD,
    PRE_UPDATE,
    POST_UPDATE,
    PRE_REMOVE,
    POST_REMOVE
}
