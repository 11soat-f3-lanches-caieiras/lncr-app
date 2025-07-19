package br.com.tp.lncr.core.commons.interfaces.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;

public interface FoodItemImageController {

    FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    void deleteImageById(Integer foodItemImageId, FoodItemDatabase foodItemDatabase);

    FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDatabase foodItemDatabase, String imageLocationPrefix);

    FoodItemImageDTO updateImageById(Integer foodItemImageId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);
}
