package br.com.tp.lanchescaieiras.commons.infraestructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lncr.integration")
public class IntegrationConfig {
    private String foodItemsUrl;
    private String customersUrl;
    private String customerOrdersUrl;

    public String getFoodItemsUrl() {
        return foodItemsUrl;
    }

    public void setFoodItemsUrl(String foodItemsUrl) {
        this.foodItemsUrl = foodItemsUrl;
    }

    public String getCustomersUrl() {
        return customersUrl;
    }

    public void setCustomersUrl(String customersUrl) {
        this.customersUrl = customersUrl;
    }

    public String getCustomerOrdersUrl() {
        return customerOrdersUrl;
    }

    public void setCustomerOrdersUrl(String customerOrdersUrl) {
        this.customerOrdersUrl = customerOrdersUrl;
    }
}
