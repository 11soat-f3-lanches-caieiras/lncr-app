package br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem;

import jakarta.persistence.*;

@Table(name = "food_item", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Entity
public class JpaFoodItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public String description;
    public Double price;
    public Integer categoryId;

    public JpaFoodItemEntity() {
    }

    public JpaFoodItemEntity(Integer id, String name, String description, Double price, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
