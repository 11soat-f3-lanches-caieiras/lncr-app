package br.com.tp.lanchescaieiras.commons.interfaces;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;

import java.util.List;

public interface FoodItemGateway {

    FoodItem create(FoodItem foodItem);

    boolean existsByName(String foodItemName);

    void saveImages(List<FoodItemImage> foodItemImages);

    void saveImagesFiles(List<FoodItemImage> foodItemImage);

    List<FoodItem> getAllFoodItems(Integer _limit, String category, Boolean includeImages,
                                   FoodItemMapper foodItemMapper);

    FoodItem getFoodItemById(Integer foodItemId, Boolean includeImages);
}