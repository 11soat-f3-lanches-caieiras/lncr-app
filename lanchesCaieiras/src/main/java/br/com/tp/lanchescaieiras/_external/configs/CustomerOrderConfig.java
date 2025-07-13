package br.com.tp.lanchescaieiras._external.configs;

import br.com.tp.lanchescaieiras._core.adapters.customerorder.CustomerOrderControllerImpl;
import br.com.tp.lanchescaieiras._core.adapters.customerorder.CustomerOrderMapper;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderDatabase;
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
    public CustomerOrderMapper customerOrderMapper() {
        return new CustomerOrderMapper();
    }

    @Bean
    public Module javaTimeModule() {
        return new JavaTimeModule();
    }

}
