package br.com.tp.lanchescaieiras._external.datasources.postgres.kitchenorder;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "kitchen_order", uniqueConstraints = @UniqueConstraint(columnNames = "customerOrderId"))
public class JpaKitchenOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerOrderId;
    private Integer statusId;
    private LocalDateTime created;
    private LocalDateTime updated;


    @Transient
    private List<JpaKitchenOrderFoodItemEntity> foodItems;

    public JpaKitchenOrderEntity(Integer id, Integer customerOrderId, Integer statusId, LocalDateTime created, LocalDateTime updated, List<JpaKitchenOrderFoodItemEntity> foodItems) {
        this.id = id;
        this.customerOrderId = customerOrderId;
        this.statusId = statusId;
        this.created = created;
        this.updated = updated;
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

    public LocalDateTime getCreated() {
        return created;
    }

    @PrePersist
    public void prePersist() {
        this.created = LocalDateTime.now();
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = LocalDateTime.now();
    }

    public List<JpaKitchenOrderFoodItemEntity> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<JpaKitchenOrderFoodItemEntity> foodItems) {
        this.foodItems = foodItems;
    }
}
