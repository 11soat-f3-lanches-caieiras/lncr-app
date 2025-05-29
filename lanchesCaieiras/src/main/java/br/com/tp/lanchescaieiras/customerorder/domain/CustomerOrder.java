package br.com.tp.lanchescaieiras.customerorder.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.PostConstruct;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerOrder {
    private Integer id;
    private String status;
    private Double totalCost;
    private CustomerOrderCustomer customer;
    private List<CustomerOrderFoodItem> foodItems;

    public CustomerOrder(Integer id, CustomerOrderStatus status, CustomerOrderCustomer customer, List<CustomerOrderFoodItem> foodItems, Double totalCost) {
        this.id = id;
        this.status = fromCustomerOrderStatus(status);
        this.customer = customer;
        this.foodItems = foodItems;
        this.totalCost = totalCost;
    }

    @PostConstruct
    public void setTotalCost() {
        this.totalCost = 0.0;
        for (CustomerOrderFoodItem foodItem : foodItems) {
            this.totalCost += foodItem.getPrice();
        }
        this.totalCost = Double.parseDouble(new DecimalFormat("#.00").format(this.totalCost).replace(",","."));
    }

    public CustomerOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }


    public CustomerOrderCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerOrderCustomer customer) {
        this.customer = customer;
    }

    public List<CustomerOrderFoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<CustomerOrderFoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public void setStatus(CustomerOrderStatus status) {
        this.status = fromCustomerOrderStatus(status);
    }

    public String fromCustomerOrderStatus(CustomerOrderStatus status) {
        return status.getDescription();
    }

}
