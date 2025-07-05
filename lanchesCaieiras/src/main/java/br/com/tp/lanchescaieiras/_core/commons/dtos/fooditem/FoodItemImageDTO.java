package br.com.tp.lanchescaieiras._core.commons.dtos.fooditem;

public class FoodItemImageDTO {
    private Integer id;
    private Integer foodItemId;
    private String _data;
    private String location;
    private String fileName;
    private String fileExtension;
    private String imageError;

    public FoodItemImageDTO() {
    }

    public FoodItemImageDTO(Integer id, Integer foodItemId, String _data, String location, String fileName, String fileExtension, String imageError) {
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

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public String getLocation() {
        return location;
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

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }
}

