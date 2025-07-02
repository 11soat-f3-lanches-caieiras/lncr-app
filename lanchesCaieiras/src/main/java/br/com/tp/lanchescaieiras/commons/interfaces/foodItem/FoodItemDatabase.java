package br.com.tp.lanchescaieiras.commons.interfaces.foodItem;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;

import java.util.List;


public interface FoodItemDatabase {

    FoodItemDTO save(FoodItemDTO foodItemDTO);

    FoodItemImageDTO save (FoodItemImageDTO foodItemImageDTO);

    FoodItemDTO create(FoodItemDTO foodItemDTO);

    boolean existsByName(String foodItemName);

    void saveImages(List<FoodItemImageDTO> foodItemImageDTOList);

    void saveImageFiles(List<FoodItemImageDTO> foodItemImageDTOList);

    List<FoodItemDTO> getAllFoodItems(Integer _limit, Integer categoryId, Boolean includeImages);

    FoodItemDTO getFoodItemById(Integer foodItemId, Boolean includeImages);

    void delete(FoodItemDTO foodItemDTO);

    List<FoodItemImageDTO> findAllFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData);

    void create(FoodItemImageDTO foodItemImageDTO);

    FoodItemImageDTO getFoodItemImageById(Integer foodItemImageId);
}
