package com.example.batch.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionConfiguration {

    @Bean
    @Primary
    @Autowired
    public PlatformTransactionManager chainedTransactionManager(
            @Qualifier("activeTransactionManager") PlatformTransactionManager firstTxManager
            , @Qualifier("inactiveTransactionManager") PlatformTransactionManager secondTxManager) {
        return new ChainedTransactionManager(firstTxManager, secondTxManager);
    }
}
