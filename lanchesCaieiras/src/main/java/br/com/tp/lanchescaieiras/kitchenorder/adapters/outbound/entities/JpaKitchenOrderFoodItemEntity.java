package br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_order_food_item")
public class JpaKitchenOrderFoodItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;
    private Integer foodItemId;
    private Double price;
    private String notes;

    public JpaKitchenOrderFoodItemEntity(Integer id, Integer orderId, Integer foodItemId, Double price, String notes) {
        this.id = id;
        this.orderId = orderId;
        this.foodItemId = foodItemId;
        this.price = price;
        this.notes = notes;
    }

    public JpaKitchenOrderFoodItemEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(Integer foodItemId) {
        this.foodItemId = foodItemId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
