package br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.utils.FoodItemImageRules;

public interface FoodItemImageController {

    FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDatabase foodItemDatabase, String imageLocationPrefix);

    FoodItemImageDTO updateImageById(Integer foodItemImageId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    void deleteImageById(Integer foodItemImageId, FoodItemDatabase foodItemDatabase);
}
