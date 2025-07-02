package br.com.tp.lanchescaieiras.commons.interfaces.foodItem;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemMapper;

import java.util.List;

public interface FoodItemGateway {

    FoodItem create(FoodItem foodItem);

    boolean existsByName(String foodItemName);

    void saveImages(List<FoodItemImage> foodItemImages);

    void saveImagesFiles(List<FoodItemImage> foodItemImage);

    List<FoodItem> getAllFoodItems(Integer _limit, String category, Boolean includeImages,
                                   FoodItemMapper foodItemMapper);

    FoodItem getFoodItemById(Integer foodItemId, Boolean includeImages);

    FoodItem getFoodItemById(Integer foodItemId);

    FoodItem saveFoodItem(FoodItem foodItem);

    void delete(FoodItem foodItem);

    List<FoodItemImage> findAllFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData);

    FoodItemImage getFoodItemImageById(Integer foodItemImageId);
}