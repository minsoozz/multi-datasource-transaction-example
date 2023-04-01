package com.example.batch.config.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QuerydslConfiguration {

    @PersistenceContext(unitName = "activeEntityManager")
    private EntityManager activeEntityManager;

    @PersistenceContext(unitName = "inactiveEntityManager")
    private EntityManager inactiveEntityManager;

    @Bean
    public JPAQueryFactory activeJpaQueryFactory() {
        return new JPAQueryFactory(activeEntityManager);
    }

    @Bean
    public JPAQueryFactory inactiveJpaQueryFactory() {
        return new JPAQueryFactory(inactiveEntityManager);
    }
}
