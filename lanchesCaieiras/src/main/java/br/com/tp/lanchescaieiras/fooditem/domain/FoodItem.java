package br.com.tp.lanchescaieiras.fooditem.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

@JsonPropertyOrder({"id", "name", "description", "price", "category", "images"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItem {
    public Integer id;
    public String name;
    public String description;
    public Double price;

    @Enumerated(EnumType.STRING)
    public FoodItemCategory category;
    public List<FoodItemImage> foodItemImages;

    public FoodItem(Integer id, String name, String description, Double price, FoodItemCategory category, List<FoodItemImage> foodItemImages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.foodItemImages = foodItemImages;
    }

    public FoodItem() {
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

    public FoodItemCategory getCategory() {
        return category;
    }

    @JsonSetter("category")
    public void setCategory(String category) {
        if(category != null) {
            this.category = FoodItemCategory.valueOf(category.toUpperCase());
        }
    }

    public void setCategory(FoodItemCategory category) {
        this.category = category;
    }



    public List<FoodItemImage> getImages() {
        return foodItemImages;
    }

    public void setImages(List<FoodItemImage> foodItemImages) {
        this.foodItemImages = foodItemImages;
    }



}
