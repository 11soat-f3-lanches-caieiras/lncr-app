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
    private LocalDateTime _created;
    private LocalDateTime _updated;

    @Transient
    private List<JpaKitchenOrderFoodItemEntity> foodItems;

    public JpaKitchenOrderEntity(Integer id, Integer customerOrderId, Integer statusId, LocalDateTime _created, LocalDateTime _updated, List<JpaKitchenOrderFoodItemEntity> foodItems) {
        this.id = id;
        this.customerOrderId = customerOrderId;
        this.statusId = statusId;
        this._created = _created;
        this._updated = _updated;
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

    public LocalDateTime get_created() {
        return _created;
    }

    public void set_created(LocalDateTime _created) {
        this._created = _created;
    }

    public LocalDateTime get_updated() {
        return _updated;
    }

    public void set_updated(LocalDateTime _updated) {
        this._updated = _updated;
    }

    public List<JpaKitchenOrderFoodItemEntity> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<JpaKitchenOrderFoodItemEntity> foodItems) {
        this.foodItems = foodItems;
    }
}
