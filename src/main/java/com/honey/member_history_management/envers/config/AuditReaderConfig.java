package com.honey.member_history_management.envers.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditReaderConfig {
    @Bean
    public AuditReader auditReader(EntityManagerFactory entityManagerFactory) {
        return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
    }
}
