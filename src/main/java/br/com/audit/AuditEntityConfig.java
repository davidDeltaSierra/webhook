package br.com.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import static java.util.Objects.isNull;

@Configuration
@RequiredArgsConstructor
public class AuditEntityConfig {
    private static AuditEntityConfig INSTANCE;
    private final ObjectProvider<AuditEntityProcessor<?>> processors;

    @EventListener(ApplicationStartedEvent.class)
    void init(ApplicationStartedEvent event) {
        INSTANCE = event.getApplicationContext()
                .getBean(AuditEntityConfig.class);
    }

    void eventDispatcher(AuditEntity<?> event) {
        processors.stream()
                .filter(it -> it.acceptHandler(event))
                .forEach(it -> it.eventProcessor(event));
    }

    static AuditEntityConfig getInstance() {
        if (isNull(INSTANCE)) {
            throw new RuntimeException(
                    "Failed in load beanFactory " +
                            "import AuditEntityConfig.class in your SpringBootApplication"
            );
        }
        return INSTANCE;
    }
}
