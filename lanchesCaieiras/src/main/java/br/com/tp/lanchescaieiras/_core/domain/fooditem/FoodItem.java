package br.com.tp.lanchescaieiras._core.domain.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;

import java.util.ArrayList;
import java.util.List;

public class FoodItem {
    public Integer id;
    public String name;
    public String description;
    public Double price;
    public FoodItemCategory category;
    public List<FoodItemImage> images = new ArrayList<>(5);

    public FoodItem(Integer id, String name, String description, Double price, FoodItemCategory category, List<FoodItemImage> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.images = images;
    }

    public FoodItem() {
    }

    public FoodItem(FoodItemDTO foodItemDTO) {
        if (foodItemDTO != null) {
            this.id = foodItemDTO.getId();
            this.name = foodItemDTO.getName();
            this.price = foodItemDTO.getPrice();
            this.description = foodItemDTO.getDescription();
            this.category = FoodItemCategory.fromDescription(foodItemDTO.getCategory());
            this.images = new ArrayList<>();
        }
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

    public void setCategory(String category) {
        if (category != null) {
            this.category = FoodItemCategory.valueOf(category.toUpperCase());
        }
    }

    public void setCategory(FoodItemCategory category) {
        this.category = category;
    }

    public List<FoodItemImage> getImages() {
        return images;
    }

    public void setImages(List<FoodItemImage> foodItemImages) {
        this.images = foodItemImages;
    }

}
