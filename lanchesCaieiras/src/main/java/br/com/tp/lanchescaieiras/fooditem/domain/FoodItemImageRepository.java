package br.com.tp.lanchescaieiras.fooditem.domain;

public interface FoodItemImageRepository {

    FoodItem saveImages(FoodItem foodItem);

    FoodItem findAllImagesByFoodItemId(FoodItem foodItemId);

    void deleteFoodItemImagesByFoodItemId(Integer foodItemId);

    FoodItemImage findImageById(Integer id);

    FoodItemImage updateImageById(Integer id, FoodItemImage foodItemImage);

}
