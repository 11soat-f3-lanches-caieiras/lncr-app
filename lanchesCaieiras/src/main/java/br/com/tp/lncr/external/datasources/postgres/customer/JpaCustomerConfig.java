package br.com.tp.lncr.external.datasources.postgres.customer;

import br.com.tp.lncr.external.configs.JpaHibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@EnableJpaRepositories(
        basePackages = "br.com.tp.lncr.external.datasources.postgres.customer",
        entityManagerFactoryRef = "postgresCustomerEntityManagerFactory",
        transactionManagerRef = "postgresCustomerTransactionManager"
)
public class JpaCustomerConfig {

    @Autowired
    private JpaHibernateConfig jpaHibernateConfig;


    @Primary
    @Bean(name = "postgresCustomerDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.postgres")
    public DataSource postgresCustomerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "postgresCustomerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresCustomerDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = jpaHibernateConfig.hibernateProperties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(postgresCustomerDataSource())
                .properties(properties)
                .packages("br.com.tp.lncr.external.datasources.postgres.customer")
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
