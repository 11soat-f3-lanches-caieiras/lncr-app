package br.com.tp.lanchescaieiras.fooditem.application.usecases;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;

import java.util.List;
import java.util.Optional;

public interface FoodItemUseCases {
    FoodItem createFoodItem(FoodItem foodItem);

    List<FoodItem> getAllFoodItems(Integer _limit, String category);

    Optional<FoodItem> getFoodItem(Integer id);

    FoodItem partialUpdateFoodItemById(Integer id, FoodItem foodItem);

    void deleteFoodItemById(Integer id);

    FoodItemImage getImageData(Integer id);

    FoodItemImage updateImageById(Integer id, FoodItemImage foodItemImage);

}
