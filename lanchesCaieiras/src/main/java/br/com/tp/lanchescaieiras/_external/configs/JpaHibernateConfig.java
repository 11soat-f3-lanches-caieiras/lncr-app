package br.com.tp.lanchescaieiras._external.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class JpaHibernateConfig {

    @Bean(name = "hibernateCustomerProperties")
    public HashMap<String, Object> hibernateProperties() {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("spring.jpa.show-sql", true);
        properties.put("hibernate.format_sql", true);
        return properties;
    }
}
