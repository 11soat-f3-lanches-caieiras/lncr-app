package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Table(name = "food_item_image")
@Entity
public class JpaFoodItemImageEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    public Integer id;
    public Integer foodItemId;
    public String fileName;

    public String getImagePath() {
        return imagePath;
    }

    @Value("${lncr.image.location-prefix}")
    private String imagePath;

    public JpaFoodItemImageEntity() {
    }

    public JpaFoodItemImageEntity(Integer id, Integer foodItemId, String fileName) {
        this.id = id;
        this.foodItemId = foodItemId;
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(Integer foodItemId) {
        this.foodItemId = foodItemId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer setImageId(Integer i, Integer foodItemId) {
        return Integer.parseInt(foodItemId.toString().concat(i.toString()));
    }

    public String setFileName(Integer imageId, String fileExtension) {
        return imageId.toString() + "." + fileExtension;
    }

    public String setLocation(String imagePath, Integer id) {
        return imagePath + "/" + id;
    }
}
