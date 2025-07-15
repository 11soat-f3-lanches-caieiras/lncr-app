package br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.utils.FoodItemImageRules;

import java.util.List;

public interface FoodItemController {

    FoodItemDTO create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    FoodItemDTO getById(Integer foodItemId, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    List<FoodItemDTO> getByIdList(List<Integer> foodItemIdList, FoodItemDatabase foodItemDatabase);

    FoodItemDTO partialUpdateById(Integer id, FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    void deleteById(Integer foodItemId, FoodItemDatabase foodItemDatabase);

    FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    List<FoodItemImageDTO> getFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules);

    void deleteImagesByFoodItemId(Integer foodItemId, FoodItemDatabase foodItemDatabase);
}
