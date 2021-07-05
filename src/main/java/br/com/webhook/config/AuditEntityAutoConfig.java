package br.com.webhook.config;

import br.com.audit.AuditEntity;
import br.com.audit.AuditEntityConfig;
import br.com.audit.AuditEntityEvent;
import br.com.audit.AuditEntityProcessor;
import br.com.webhook.model.AbstractEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import(AuditEntityConfig.class)
public class AuditEntityAutoConfig {

    @Bean
    AuditEntityProcessor<AbstractEntity> auditEntityProcessorAbstractEntity() {
        return new AuditEntityProcessor<>() {
            @Override
            public boolean accept(AuditEntityEvent auditEntity) {
                return true;
            }

            @Override
            public void process(AuditEntity<AbstractEntity> auditEntity) {
                log.info("AbstractEntity: {}", auditEntity);
            }
        };
    }
}
