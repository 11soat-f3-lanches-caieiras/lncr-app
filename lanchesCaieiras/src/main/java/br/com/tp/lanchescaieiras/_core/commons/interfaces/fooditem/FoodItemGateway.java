package br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem;

import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

import java.util.List;

public interface FoodItemGateway {

    boolean existsByName(String foodItemName);

    FoodItem createFoodItemImage(FoodItem foodItem);

    FoodItemImage createFoodItemImage(FoodItemImage foodItemImage);

    void deleteFoodItemImage(FoodItem foodItem);

    void deleteFoodItemImage(FoodItemImage foodItemImage);

    void deleteImagesByFoodItemId(Integer foodItemId);

    void deleteImageFile(String fileName);

    List<FoodItem> getAllFoodItems(Integer _limit, String category, Boolean includeImages);

    List<FoodItemImage> getAllImagesByFoodItemId(Integer foodItemId, Boolean includeData);

    FoodItem getFoodItemById(Integer foodItemId);

    FoodItem getFoodItemById(Integer foodItemId, Boolean includeImages);

    List<FoodItem> getFoodItemByIdList(List<Integer> foodItemIds);

    FoodItemImage getFoodItemImageById(Integer foodItemImageId);

    FoodItem saveFoodItem(FoodItem foodItem);

}