package br.com.tp.lanchescaieiras._external.configs;

import br.com.tp.lanchescaieiras._core.adapters.customer.CustomerControllerImpl;
import br.com.tp.lanchescaieiras._core.adapters.customer.CustomerMapper;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customer.CustomerDatabase;
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
    public CustomerMapper customerMapper(){
        return new CustomerMapper();
    }




}
