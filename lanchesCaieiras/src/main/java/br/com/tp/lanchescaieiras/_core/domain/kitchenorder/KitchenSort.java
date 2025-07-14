package br.com.tp.lanchescaieiras._core.domain.kitchenorder;


import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.SortInterface;

import java.time.LocalDateTime;
import java.util.List;

public class KitchenSort implements SortInterface {
    private Integer id;
    private LocalDateTime _created;
    private LocalDateTime _updated;
    private Integer customerOrderId;
    private String status;
    private List<KitchenOrderFoodItem> foodItems;


    public KitchenSort(Integer id, Integer customerOrderId, String status, List<KitchenOrderFoodItem> foodItems, LocalDateTime _created, LocalDateTime _updated) {
        this.id = id;
        this.customerOrderId = customerOrderId;
        this.status = status;
        this.foodItems = foodItems;
        this._created = _created;
        this._updated = _updated;
    }

    public KitchenSort() {
    }

    public KitchenSort(KitchenOrderDTO dto) {
        this.id = dto.getId();
        this.customerOrderId = dto.getCustomerOrderId();
        this.status = dto.getStatus();
        if (dto.getFoodItems() != null) {
            this.foodItems = dto.getFoodItems().stream()
                .map(KitchenOrderFoodItem::new)
                .toList();
        }
        if (dto.get_created() != null) {
            this._created = dto.get_created();
        }
        if (dto.get_updated() != null) {
            this._updated = dto.get_updated();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.status = KitchenOrderStatus.fromDescription(status).getDescription();
    }

    public List<KitchenOrderFoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<KitchenOrderFoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}

