package br.com.tp.lanchescaieiras._external.configs;

import br.com.tp.lanchescaieiras._core.adapters.customer.CustomerControllerImpl;
import br.com.tp.lanchescaieiras._core.applications.payment.mappers.PaymentMapper;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customer.CustomerDatabase;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customer.JpaCustomerPostgresMapper;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customer.JpaCustomerPostgresRepository;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customer.JpaCustomerPostgresReposityImpl;
import br.com.tp.lanchescaieiras._external.datasources.postgres.payment.JpaPaymentRepository;
import br.com.tp.lanchescaieiras._external.datasources.postgres.payment.JpaPaymentsRepositoryImpl;
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
    public CustomerControllerImpl customerControllerImpl(CustomerDatabase customerDatabase) {
        return new CustomerControllerImpl(customerDatabase);
    }

    @Bean
    JpaCustomerPostgresMapper jpaCustomerPostgresMapper() {
        return new JpaCustomerPostgresMapper();
    }

    @Bean
    public JpaCustomerPostgresReposityImpl jpaCustomerPostgresReposityImpl(JpaCustomerPostgresRepository repository, JpaCustomerPostgresMapper mapper) {
        return new JpaCustomerPostgresReposityImpl(repository, mapper);
    }

    @Bean
    public JpaPaymentsRepositoryImpl jpaPaymentsRepositoryImpl(JpaPaymentRepository jpaPaymentsRepository, PaymentMapper paymentMapper) {
        return new JpaPaymentsRepositoryImpl(jpaPaymentsRepository, paymentMapper);
    }
}
