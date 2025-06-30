package br.com.tp.lanchescaieiras.customer.external.config;

import br.com.tp.lanchescaieiras.customer.adapters.CustomerControllerImpl;
import br.com.tp.lanchescaieiras.customer.external.datasources.postgres.JpaCustomerPostgresMapper;
import br.com.tp.lanchescaieiras.customer.external.datasources.postgres.JpaCustomerPostgresRepository;
import br.com.tp.lanchescaieiras.customer.external.datasources.postgres.JpaCustomerPostgresReposityImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.repositories.JpaPaymentRepository;
import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.repositories.JpaPaymentsRepositoryImpl;
import br.com.tp.lanchescaieiras.payments.mercadopago.applications.mappers.PaymentMapper;
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

    @Bean
    public JpaPaymentsRepositoryImpl jpaPaymentsRepositoryImpl(JpaPaymentRepository jpaPaymentsRepository, PaymentMapper paymentMapper) {
        return new JpaPaymentsRepositoryImpl(jpaPaymentsRepository, paymentMapper);
    }
}
