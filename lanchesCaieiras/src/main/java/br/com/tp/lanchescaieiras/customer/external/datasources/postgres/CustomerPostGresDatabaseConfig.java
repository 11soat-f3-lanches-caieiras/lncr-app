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
        basePackages = {
                "br.com.tp.lanchescaieiras.customer.external.datasources.postgres",
                "br.com.tp.lanchescaieiras.commons",
                "br.com.tp.lanchescaieiras.customerorder",
                "br.com.tp.lanchescaieiras.kitichenorder",
                "br.com.tp.lanchescaieiras.payments.mercadopago"
        },
        entityManagerFactoryRef = "postgresCustomerEntityManagerFactory",
        transactionManagerRef = "postgresCustomerTransactionManager"
)
public class CustomerPostGresDatabaseConfig {

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
                .packages("br.com.tp.lanchescaieiras.customer.external.datasources.postgres",
                        "br.com.tp.lanchescaieiras.commons.adapters.outbound.entities",
                        "br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities",
                        "br.com.tp.lanchescaieiras.kitichenorder.adapters..outbound.entities",
                        "br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.entities")
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
