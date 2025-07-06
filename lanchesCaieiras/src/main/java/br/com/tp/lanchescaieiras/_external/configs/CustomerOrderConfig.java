package br.com.tp.lanchescaieiras._external.configs;

import br.com.tp.lanchescaieiras._core.adapters.customerorder.CustomerOrderControllerImpl;
import br.com.tp.lanchescaieiras._core.adapters.customerorder.CustomerOrderMapper;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lanchescaieiras._external.dataproxy.CustomerOrderDataProxy;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder.JpaCustomerOrderFoodItemPostgresRepositoryImpl;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder.JpaCustomerOrderPostgresMapper;
import br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder.JpaCustomerOrderPostgresRepositoryImpl;
import br.com.tp.lanchescaieiras._external.integrations.customer.CustomerIntegrationImpl;
import br.com.tp.lanchescaieiras._external.integrations.fooditem.FoodItemIntegrationImpl;
import br.com.tp.lanchescaieiras._external.integrations.payment.PaymentIntegrationImpl;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lncr.customer-order")
public class CustomerOrderConfig {

    private String locationPrefix;

    public String getLocationPrefix() {
        return locationPrefix;
    }

    public void setLocationPrefix(String locationPrefix) {
        this.locationPrefix = locationPrefix;
    }

    @Bean
    public CustomerOrderControllerImpl customerOrderControllerImpl(CustomerOrderDatabase customerOrderDatabase, CustomerOrderMapper customerOrderMapper){
        return new CustomerOrderControllerImpl(customerOrderDatabase);
    }

    @Bean
    public JpaCustomerOrderPostgresMapper jpaCustomerOrderPostgresMapper() {
        return new JpaCustomerOrderPostgresMapper();
    }

    @Bean
    public CustomerOrderDataProxy customerOrderDataProxy(JpaCustomerOrderPostgresRepositoryImpl jpaCustomerOrderPostgresRepository, JpaCustomerOrderFoodItemPostgresRepositoryImpl jpaCustomerOrderFoodItemPostgresRepository, CustomerIntegrationImpl customerIntegration, FoodItemIntegrationImpl foodItemIntegration, PaymentIntegrationImpl paymentIntegration){
        return new CustomerOrderDataProxy(jpaCustomerOrderPostgresRepository, jpaCustomerOrderFoodItemPostgresRepository,customerIntegration,foodItemIntegration, paymentIntegration);
    }

    @Bean
    public CustomerOrderMapper customerOrderMapper(){
        return new CustomerOrderMapper();
    }

    @Bean
    public Module javaTimeModule() {
        return new JavaTimeModule();
    }

}
