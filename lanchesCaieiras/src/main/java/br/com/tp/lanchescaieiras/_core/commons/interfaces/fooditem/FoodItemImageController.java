package br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;
import br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem.FoodItemDataProxy;

public interface FoodItemImageController {

    FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDataProxy foodItemDatabase, FoodItemImageRules foodItemImageRules);

    FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDataProxy foodItemDatabase, String imageLocationPrefix);


}
