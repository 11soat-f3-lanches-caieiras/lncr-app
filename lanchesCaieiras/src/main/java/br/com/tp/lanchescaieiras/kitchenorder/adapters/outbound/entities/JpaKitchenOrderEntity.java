package br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "kitchen_order")
public class JpaKitchenOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerOrderId;
    private Integer statusId;

    @Transient
    private List<JpaKitchenOrderFoodItemEntity> foodItems;

    public JpaKitchenOrderEntity(Integer id, Integer customerOrderId, Integer statusId, List<JpaKitchenOrderFoodItemEntity> foodItems) {
        this.id = id;
        this.customerOrderId = customerOrderId;
        this.statusId = statusId;
        this.foodItems = foodItems;
    }

    public JpaKitchenOrderEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public List<JpaKitchenOrderFoodItemEntity> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<JpaKitchenOrderFoodItemEntity> foodItems) {
        this.foodItems = foodItems;
    }
}
