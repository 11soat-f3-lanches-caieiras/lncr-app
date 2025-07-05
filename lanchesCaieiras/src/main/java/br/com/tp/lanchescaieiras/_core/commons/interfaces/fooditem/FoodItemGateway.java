package br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem;

import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

import java.util.List;

public interface FoodItemGateway {

    FoodItem create(FoodItem foodItem);

    FoodItemImage create(FoodItemImage foodItemImage);

    boolean existsByName(String foodItemName);

    void saveImages(List<FoodItemImage> foodItemImages);

    void saveImagesFiles(List<FoodItemImage> foodItemImage);

    List<FoodItem> getAllFoodItems(Integer _limit, String category, Boolean includeImages);

    FoodItem getFoodItemById(Integer foodItemId, Boolean includeImages);

    FoodItem getFoodItemById(Integer foodItemId);

    FoodItem saveFoodItem(FoodItem foodItem);

    void delete(FoodItem foodItem);

    List<FoodItemImage> getAllImagesByFoodItemId(Integer foodItemId, Boolean includeData);

    FoodItemImage getFoodItemImageById(Integer foodItemImageId);

    void deleteImagesByFoodItemId(Integer foodItemId);
}