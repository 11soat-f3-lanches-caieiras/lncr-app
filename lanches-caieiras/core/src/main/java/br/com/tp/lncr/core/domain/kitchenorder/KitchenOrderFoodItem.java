package br.com.tp.lncr.core.domain.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderFoodItemDTO;

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

    public KitchenOrderFoodItem(KitchenOrderFoodItemDTO dto) {
        this.id = dto.getId();
        this.kitchenOrderId = dto.getKitchenOrderId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.notes = dto.getNotes();
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
