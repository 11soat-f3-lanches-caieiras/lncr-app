package br.com.tp.lanchescaieiras.fooditem.application;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.enums.FoodItemCategory;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemGatewayImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;

import java.util.List;
import java.util.Optional;

public class GetFoodItemUseCase {
    public List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages,
                                    FoodItemGatewayImpl foodItemGateway,
                                    FoodItemMapper foodItemMapper) {
        if (_limit == null) {
            _limit = 10; // Default limit
        }else {
            if (_limit < 1 || _limit > 50) {
                throw new FoodItemException("Limite deve estar entre 1 e 50", 400);
            }
        }

        List<FoodItem> foodItemList = foodItemGateway.getAllFoodItems(_limit, category, includeImages, foodItemMapper);
        return foodItemList.stream()
                .map(foodItemMapper::domainToDto)
                .toList();
    }
}
