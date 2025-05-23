package br.com.tp.lanchescaieiras.fooditem.domain;

import java.util.List;
import java.util.Optional;

public interface FoodItemRepository {

    FoodItem save(FoodItem foodItem);

    List<FoodItem> findAll(Integer _limit);

    List<FoodItem> findAllByCategory(Integer _limit, String category);

    Optional <FoodItem> findById(Integer id);

    Optional <FoodItem> partialUpdateFoodItemById(Integer id, FoodItem foodItem);

    void deleteFoodItemById(Integer id);



}
