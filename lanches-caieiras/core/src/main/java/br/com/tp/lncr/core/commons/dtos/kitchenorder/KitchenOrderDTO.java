package br.com.tp.lncr.core.commons.dtos.kitchenorder;

import java.time.LocalDateTime;
import java.util.List;

public class KitchenOrderDTO {
    private Integer id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Integer customerOrderId;
    private String status;
    private List<KitchenOrderFoodItemDTO> foodItems;

    public KitchenOrderDTO() {}

    public KitchenOrderDTO(Integer id, Integer customerOrderId, String status, List<KitchenOrderFoodItemDTO> foodItems, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.customerOrderId = customerOrderId;
        this.status = status;
        this.foodItems = foodItems;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
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

    public List<KitchenOrderFoodItemDTO> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<KitchenOrderFoodItemDTO> foodItems) {
        this.foodItems = foodItems;
    }

    @Override
    public String toString() {
        return "KitchenOrderDTO{" +
                "id=" + id +
                ", customerOrderId=" + customerOrderId +
                ", status='" + status + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", foodItems=" + foodItems.stream().toString() +
                '}';
    }
}


