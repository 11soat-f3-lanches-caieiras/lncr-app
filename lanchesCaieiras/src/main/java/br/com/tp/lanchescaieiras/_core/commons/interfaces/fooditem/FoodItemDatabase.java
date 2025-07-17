package br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;

import java.util.List;


public interface FoodItemDatabase {

    boolean existsByName(String foodItemName);

    FoodItemDTO create(FoodItemDTO foodItemDTO);

    void create(FoodItemImageDTO foodItemImageDTO);

    void delete(FoodItemDTO foodItemDTO);

    void delete(FoodItemImageDTO foodItemImageDTO);

    void deleteImageFile(String fileName);

    void deleteImagesByFoodItemId(Integer foodItemId);

    List<FoodItemImageDTO> findAllFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData);

    List<FoodItemDTO> findAllFoodItems(Integer _limit, Integer categoryId, Boolean includeImages);

    FoodItemDTO findFoodItemById(Integer foodItemId, Boolean includeImages);

    List<FoodItemDTO> findFoodItemByIdList(List<Integer> foodItemIds);

    FoodItemImageDTO findFoodItemImageById(Integer foodItemImageId);

    FoodItemDTO save(FoodItemDTO foodItemDTO);

    FoodItemImageDTO save(FoodItemImageDTO foodItemImageDTO);


}
