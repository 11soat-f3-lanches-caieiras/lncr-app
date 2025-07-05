package br.com.tp.lanchescaieiras._external.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lncr.integration")
public class IntegrationConfig {
    private String foodItemsUrl;
    private String customersUrl;
    private String customerOrderUrl;
    private String kitchenOrderUrl;
    private String paymentUrl;

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

    public String getCustomerOrderUrl() {
        return customerOrderUrl;
    }

    public void setCustomerOrderUrl(String customerOrderUrl) {
        this.customerOrderUrl = customerOrderUrl;
    }

    public String getKitchenOrderUrl() {
        return kitchenOrderUrl;
    }

    public void setKitchenOrderUrl(String kitchenOrderUrl) {
        this.kitchenOrderUrl = kitchenOrderUrl;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }
}
