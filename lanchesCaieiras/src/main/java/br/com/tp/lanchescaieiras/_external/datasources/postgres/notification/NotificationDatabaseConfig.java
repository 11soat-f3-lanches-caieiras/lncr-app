package br.com.tp.lanchescaieiras._external.datasources.postgres.notification;

import br.com.tp.lanchescaieiras._external.configs.JpaHibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@EnableJpaRepositories(
        basePackages = "br.com.tp.lanchescaieiras._external.datasources.postgres.notification",
        entityManagerFactoryRef = "postgresNotificationEntityMan" +
                "agerFactory",
        transactionManagerRef = "postgresNotificationTransactionManager"
)
public class NotificationDatabaseConfig {

    @Autowired
    private JpaHibernateConfig jpaHibernateConfig;


    @Bean(name = "postgresNotificationDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.postgres")
    public DataSource notificationPostgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresNotificationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresNotificationDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = jpaHibernateConfig.hibernateProperties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(notificationPostgresDataSource())
                .properties(properties)
                .packages("br.com.tp.lanchescaieiras._external.datasources.postgres.notification")
                .persistenceUnit("NotificationPostgres")
                .build();
    }

    @Bean(name = "postgresNotificationTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresNotificationEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
