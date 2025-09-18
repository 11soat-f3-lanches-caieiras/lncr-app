package br.com.tp.lncr.app.datasources.postgres.payment;

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
        basePackages = "br.com.tp.lncr.app.datasources.postgres.payment.mercadopago",
        entityManagerFactoryRef = "postgresPaymentEntityManagerFactory",
        transactionManagerRef = "postgresPaymentTransactionManager"
)
public class JpaPaymentConfig {

    @Autowired
    private JpaHibernateConfig jpaHibernateConfig;


    @Bean(name = "paymentPostgresDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.postgres")
    public DataSource paymentPostgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresPaymentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("paymentPostgresDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = jpaHibernateConfig.hibernateProperties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(paymentPostgresDataSource())
                .properties(properties)
                .packages("br.com.tp.lncr.app.datasources.postgres.payment.mercadopago")
                .persistenceUnit("PaymentPostgres")
                .build();
    }

    @Bean(name = "postgresPaymentTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresPaymentEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
