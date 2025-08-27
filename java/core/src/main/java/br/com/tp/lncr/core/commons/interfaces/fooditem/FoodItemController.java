package br.com.tp.lncr.core.commons.interfaces.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;

import java.util.List;

public interface FoodItemController {

    FoodItemDTO create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    void deleteById(Integer foodItemId, FoodItemDatabase foodItemDatabase);

    void deleteImagesByFoodItemId(Integer foodItemId, FoodItemDatabase foodItemDatabase);

    List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    FoodItemDTO getById(Integer foodItemId, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    List<FoodItemDTO> getByIdList(List<Integer> foodItemIdList, FoodItemDatabase foodItemDatabase);

    List<FoodItemImageDTO> getFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    FoodItemDTO partialUpdateById(Integer id, FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);
}
