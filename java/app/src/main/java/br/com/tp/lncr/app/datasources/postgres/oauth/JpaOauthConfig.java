package br.com.tp.lncr.app.datasources.postgres.oauth;

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


@SuppressWarnings("unused")
@Configuration
@EnableJpaRepositories(
        basePackages = "br.com.tp.lncr.app.datasources.postgres.oauth",
        entityManagerFactoryRef = "postgresOauthEntityManagerFactory",
        transactionManagerRef = "postgresOauthTransactionManager"
)
public class JpaOauthConfig {

    @Autowired
    private JpaHibernateConfig jpaHibernateConfig;


    @Bean(name = "postgresOauthDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.postgres")
    public DataSource oauthPostgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresOauthEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresOauthDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = jpaHibernateConfig.hibernateProperties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(oauthPostgresDataSource())
                .properties(properties)
                .packages("br.com.tp.lncr.app.datasources.postgres.oauth")
                .persistenceUnit("OauthPostgres")
                .build();
    }

    @Bean(name = "postgresOauthTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresOauthEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
