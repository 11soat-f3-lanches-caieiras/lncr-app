package br.com.tp.lanchescaieiras.commons.interfaces.foodItem;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;

import java.util.List;

public interface FoodItemController {

    FoodItemDTO create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig);

    List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig);

    FoodItemDTO getById(Integer foodItemId, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig);

    FoodItemDTO partialUpdateById(Integer id, FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig);

    void deleteById(Integer foodItemId, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig);

    FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig);

    List<FoodItemImageDTO> getFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig);
}
