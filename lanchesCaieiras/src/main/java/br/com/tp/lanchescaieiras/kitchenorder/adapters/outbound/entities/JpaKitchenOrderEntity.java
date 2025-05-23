package br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "customer_order")
public class JpaKitchenOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double totalCost = 0.0;
    private Integer statusId;
    private Integer customerId;

    @Transient
    private List<JpaKitchenOrderFoodItemEntity> foodItems;

    public JpaKitchenOrderEntity(Integer id, Double totalCost, Integer statusId, Integer customerId, List<JpaKitchenOrderFoodItemEntity> foodItems) {
        this.id = id;
        this.totalCost = totalCost;
        this.statusId = statusId;
        this.customerId = customerId;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<JpaKitchenOrderFoodItemEntity> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<JpaKitchenOrderFoodItemEntity> foodItems) {
        this.foodItems = foodItems;
    }
}
