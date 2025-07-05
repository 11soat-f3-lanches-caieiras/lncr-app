package br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem;

import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

import java.util.List;

public interface FoodItemGateway {

    boolean existsByName(String foodItemName);

    void delete(FoodItem foodItem);
    void delete(FoodItemImage foodItemImage);
    void deleteImagesByFoodItemId(Integer foodItemId);
    void deleteImageFile(String fileName);

    List<FoodItem> getAllFoodItems(Integer _limit, String category, Boolean includeImages);
    List<FoodItemImage> getAllImagesByFoodItemId(Integer foodItemId, Boolean includeData);

    FoodItem getFoodItemById(Integer foodItemId, Boolean includeImages);
    FoodItem getFoodItemById(Integer foodItemId);
    FoodItemImage getFoodItemImageById(Integer foodItemImageId);

    FoodItem save(FoodItem foodItem);
    FoodItemImage save(FoodItemImage foodItemImage);
    FoodItem saveFoodItem(FoodItem foodItem);


}