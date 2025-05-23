package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.stereotype.Component;

@Component
@Table(name = "food_item_image")
@Entity

public class JpaFoodItemImageEntity {
    @Id
    public Integer id;

    public Integer foodItemId;

    @Transient
    public String _data;

    @Transient
    public String location;

    public String fileName;

    @Transient
    public String fileExtension;

    public JpaFoodItemImageEntity() {
    }

    public JpaFoodItemImageEntity(Integer id, Integer foodItemId, String _data, String location, String fileName, String fileExtension, String imagePath) {
        this.id = id;
        this.foodItemId = foodItemId;
        this._data = _data;
        this.location = location;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public Integer setImageId(Integer i, Integer foodItemId) {
        return Integer.parseInt(foodItemId.toString().concat(i.toString()));
    }

    public String setFileName(Integer imageId, String fileExtension) {
        return imageId.toString() + "." + fileExtension;
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

    public void set_data(String _data) {
        this._data = _data;
    }

    public String get_data() {
        return _data;
    }

   public void setLocation(String location) {
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }


}
