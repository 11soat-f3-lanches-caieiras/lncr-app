package br.com.tp.lanchescaieiras.commons.interfaces;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;

import java.util.List;


public interface FoodItemDatabase {

    FoodItemDTO save(FoodItemDTO foodItemDTO);

    FoodItemDTO create(FoodItemDTO foodItemDTO);

    boolean existsByName(String foodItemName);

    void saveImages(List<FoodItemImageDTO> foodItemImageDTOList);

    void saveImageFiles(List<FoodItemImageDTO> foodItemImageDTOList);

    List<FoodItemDTO> getAllFoodItems(Integer _limit, Integer categoryId, Boolean includeImages);

    FoodItemDTO getFoodItemById(Integer foodItemId, Boolean includeImages);
}
