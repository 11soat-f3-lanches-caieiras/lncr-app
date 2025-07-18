package br.com.tp.lncr.external.datasources.postgres.kitchenorder;

import br.com.tp.lncr.external.configs.JpaHibernateConfig;
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
        basePackages = "br.com.tp.lncr.external.datasources.postgres.kitchenorder",
        entityManagerFactoryRef = "postgresKitchenOrderEntityMan" +
                "agerFactory",
        transactionManagerRef = "postgresKitchenOrderTransactionManager"
)
public class KitchenOrderDatabaseConfig {

    @Autowired
    private JpaHibernateConfig jpaHibernateConfig;


    @Bean(name = "postgresKitchenOrderDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.postgres")
    public DataSource kitchenOrderPostgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresKitchenOrderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresKitchenOrderDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = jpaHibernateConfig.hibernateProperties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(kitchenOrderPostgresDataSource())
                .properties(properties)
                .packages("br.com.tp.lncr.external.datasources.postgres.kitchenorder")
                .persistenceUnit("KitchenOrderPostgres")
                .build();
    }

    @Bean(name = "postgresKitchenOrderTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresKitchenOrderEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
