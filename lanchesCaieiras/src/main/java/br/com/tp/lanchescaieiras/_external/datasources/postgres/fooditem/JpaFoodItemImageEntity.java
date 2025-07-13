package br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem;

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
    public String fileName;

    @Transient
    public String _data;

    @Transient
    public String location;

    @Transient
    public String fileExtension;

    @Transient
    public String imageError;

    public JpaFoodItemImageEntity() {
    }

    public JpaFoodItemImageEntity(Integer id, Integer foodItemId, String _data, String location, String fileName, String fileExtension, String imageError) {
        this.id = id;
        this.foodItemId = foodItemId;
        this._data = _data;
        this.location = location;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.imageError = imageError;
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

    public String getLocation() {
        return location;
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

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }


}
