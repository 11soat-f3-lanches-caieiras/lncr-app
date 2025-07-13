package br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem;

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
        basePackages = "br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem",
        entityManagerFactoryRef = "postgresFoodItemEntityManagerFactory",
        transactionManagerRef = "postgresFoodItemTransactionManager"
)
public class JpaFoodItemConfig {

    @Autowired
    private JpaHibernateConfig jpaHibernateConfig;


    @Bean(name = "postgresFoodItemDataSource")
    @ConfigurationProperties(prefix = "spring.datasources.postgres")
    public DataSource foodItemPostgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresFoodItemEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresFoodItemDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = jpaHibernateConfig.hibernateProperties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(foodItemPostgresDataSource())
                .properties(properties)
                .packages("br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem")
                .persistenceUnit("FoodItemPostgres")
                .build();
    }

    @Bean(name = "postgresFoodItemTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresFoodItemEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
