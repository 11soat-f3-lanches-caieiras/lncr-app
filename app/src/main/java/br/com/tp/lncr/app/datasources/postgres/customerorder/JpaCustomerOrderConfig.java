package br.com.tp.lncr.app.datasources.postgres.customerorder;

import br.com.tp.lncr.app.configs.JpaHibernateConfig;
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
        basePackages = "br.com.tp.lncr.app.datasources.postgres.customerorder",
        entityManagerFactoryRef = "postgresCustomerOrderEntityManagerFactory",
        transactionManagerRef = "postgresCustomerOrderTransactionManager"
)
public class JpaCustomerOrderConfig {

    @Autowired
    private JpaHibernateConfig jpaHibernateConfig;


    @Bean(name = "postgresCustomerOrderDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.postgres")
    public DataSource postgresCustomerOrderDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresCustomerOrderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresCustomerOrderDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = jpaHibernateConfig.hibernateProperties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(postgresCustomerOrderDataSource())
                .properties(properties)
                .packages("br.com.tp.lncr.app.datasources.postgres.customerorder")
                .persistenceUnit("CustomerOrderPostgres")
                .build();
    }

    @Bean(name = "postgresCustomerOrderTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresCustomerOrderEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
