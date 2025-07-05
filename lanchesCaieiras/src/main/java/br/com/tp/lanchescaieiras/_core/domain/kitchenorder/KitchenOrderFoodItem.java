package br.com.tp.lanchescaieiras._core.domain.kitchenorder;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenOrderFoodItem {
    public Integer id;
    public Integer kitchenOrderId;
    public String name;
    public String description;
    public String notes;

    public KitchenOrderFoodItem(Integer id, Integer kitchenOrderId, String name, String description, String notes) {
        this.id = id;
        this.kitchenOrderId = kitchenOrderId;
        this.name = name;
        this.description = description;
        this.notes = notes;
    }

    public KitchenOrderFoodItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKitchenOrderId() {
        return kitchenOrderId;
    }

    public void setKitchenOrderId(Integer kitchenOrderId) {
        this.kitchenOrderId = kitchenOrderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
