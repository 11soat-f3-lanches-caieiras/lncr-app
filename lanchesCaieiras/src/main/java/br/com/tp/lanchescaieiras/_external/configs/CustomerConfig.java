package br.com.tp.lanchescaieiras._external.configs;

import br.com.tp.lanchescaieiras._core.adapters.customer.CustomerControllerImpl;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customer.JpaCustomerPostgresMapper;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customer.JpaCustomerPostgresRepository;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customer.JpaCustomerPostgresReposityImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lncr.customer")
public class CustomerConfig {
    private String locationPrefix;

    public CustomerConfig() {
    }

    public String getLocationPrefix() {
        return locationPrefix;
    }

    public void setLocationPrefix(String locationPrefix) {
        this.locationPrefix = locationPrefix;
    }

    @Bean
    public CustomerControllerImpl customerControllerImpl() {
        return new CustomerControllerImpl();
    }

    @Bean
    JpaCustomerPostgresMapper jpaCustomerPostgresMapper() {
        return new JpaCustomerPostgresMapper();
    }

    @Bean
    public JpaCustomerPostgresReposityImpl jpaCustomerPostgresReposityImpl(JpaCustomerPostgresRepository repository, JpaCustomerPostgresMapper mapper) {
        return new JpaCustomerPostgresReposityImpl(repository, mapper);
    }


}
