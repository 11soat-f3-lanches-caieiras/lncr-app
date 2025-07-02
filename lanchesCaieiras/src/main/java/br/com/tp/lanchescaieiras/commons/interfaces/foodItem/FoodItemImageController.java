package br.com.tp.lanchescaieiras.commons.interfaces.foodItem;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;

public interface FoodItemImageController {

    FoodItemImageDTO create(Integer foodItemId,FoodItemImageDTO foodItemImageDTO, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig);

    FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig);




}
