package br.com.tp.lanchescaieiras.commons.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItemDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private List<FoodItemImageDTO> images;

    public FoodItemDTO() {}

    public FoodItemDTO(Integer id, String name, String description, Double price, String category, List<FoodItemImageDTO> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.images = images;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public List<FoodItemImageDTO> getImages() { return images; }
    public void setImages(List<FoodItemImageDTO> images) { this.images = images; }
}

