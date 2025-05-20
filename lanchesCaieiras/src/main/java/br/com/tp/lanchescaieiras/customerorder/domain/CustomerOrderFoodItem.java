package br.com.tp.lanchescaieiras.customerorder.domain;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemCategory;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerOrderFoodItem {
    public Integer id;
    public String name;
    public String description;
    public Double price;
    public String notes;

    public CustomerOrderFoodItem(Integer id, String name, String description, Double price, String notes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public CustomerOrderFoodItem() {
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
