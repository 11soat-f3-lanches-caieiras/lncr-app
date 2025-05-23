package br.com.tp.lanchescaieiras.kitchenorder.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenOrderFoodItem {
    public Integer id;
    public String name;
    public String description;
    public String notes;

    public KitchenOrderFoodItem(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public KitchenOrderFoodItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
