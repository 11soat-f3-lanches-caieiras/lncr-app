package br.com.tp.lanchescaieiras.fooditem.application;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemGatewayImpl;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemMapper;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;

public class UpdateFoodItemUseCase {

    public FoodItemDTO partialUpdateById(Integer foodItemId, FoodItemDTO foodItemDTO, FoodItemGatewayImpl foodItemGateway, FoodItemMapper foodItemMapper) {
        FoodItem existFoodItem = foodItemGateway.getFoodItemById(foodItemId);
        if (existFoodItem == null){
            throw new FoodItemException("Não encontrado Item de alimentação com Id: "+ foodItemId, 404);
        }
        boolean foodItemWasChanged = false;

        if (foodItemDTO.getDescription() != null && !foodItemDTO.getDescription().equals(existFoodItem.getDescription())){
            existFoodItem.setDescription(foodItemDTO.getDescription());
            foodItemWasChanged = true;
        }

        if (foodItemDTO.getPrice() != null && !foodItemDTO.getPrice().equals(existFoodItem.getPrice())){
            existFoodItem.setPrice(foodItemDTO.getPrice());
            foodItemWasChanged = true;
        }
        if (foodItemWasChanged) {
            existFoodItem = foodItemGateway.saveFoodItem(existFoodItem);
            return foodItemMapper.domainToDto(existFoodItem);
        }
        else {
            throw new FoodItemException("Não identificada mudança na descrição ou no preço do item de alimentação, por favor revisar dados da atualização.",400);
        }

    }
}
