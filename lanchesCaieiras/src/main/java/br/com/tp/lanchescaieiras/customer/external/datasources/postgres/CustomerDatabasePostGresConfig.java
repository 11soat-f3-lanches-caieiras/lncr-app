package br.com.tp.lanchescaieiras.customer.external.datasources.postgres;

import br.com.tp.lanchescaieiras.commons.infraestructure.config.JpaHibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@EnableJpaRepositories(
        basePackages = "br.com.tp.lanchescaieiras",
        entityManagerFactoryRef = "postgresCustomerEntityManagerFactory",
        transactionManagerRef = "postgresCustomerTransactionManager"
)
public class CustomerDatabasePostGresConfig {

    @Autowired
    private JpaHibernateConfig jpaHibernateConfig;


    @Primary
    @Bean(name = "customerPostgresDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.postgres")
    public DataSource customerPostgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "postgresCustomerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("customerPostgresDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = jpaHibernateConfig.hibernateCustomerProperties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(customerPostgresDataSource())
                .properties(properties)
                .packages("br.com.tp.lanchescaieiras")
                .persistenceUnit("CustomerPostgres")
                .build();
    }

    @Primary
    @Bean(name = "postgresCustomerTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresCustomerEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
