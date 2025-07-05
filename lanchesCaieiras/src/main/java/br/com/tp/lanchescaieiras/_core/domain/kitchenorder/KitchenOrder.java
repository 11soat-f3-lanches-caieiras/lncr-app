package br.com.tp.lanchescaieiras._core.domain.kitchenorder;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenOrder {
    private Integer id;
    private Integer customerOrderId;
    private String status;
    private List<KitchenOrderFoodItem> foodItems;

    public KitchenOrder(Integer id, Integer customerOrderId, KitchenOrderStatus status, List<KitchenOrderFoodItem> foodItems) {
        this.id = id;
        this.customerOrderId = customerOrderId;
        this.status = fromKitchenOrderStatus(status);
        this.foodItems = foodItems;
    }

    public KitchenOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<KitchenOrderFoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<KitchenOrderFoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public void setStatus(KitchenOrderStatus status) {
        this.status = fromKitchenOrderStatus(status);
    }

    public String fromKitchenOrderStatus(KitchenOrderStatus status) {
        return status.getDescription();
    }


}
