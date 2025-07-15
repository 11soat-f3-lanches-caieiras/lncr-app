package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;

public class UpdateFoodItemUseCase {

    private final FoodItemGateway foodItemGateway;

    public UpdateFoodItemUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public FoodItem partialUpdateById(Integer foodItemId, FoodItemDTO foodItemDTO) {
        FoodItem existFoodItem = foodItemGateway.getFoodItemById(foodItemId);
        if (existFoodItem == null) {
            throw new FoodItemException("Não encontrado Item de alimentação com Id: " + foodItemId, 404);
        }
        boolean foodItemWasChanged = false;

        if (foodItemDTO.getDescription() != null && !foodItemDTO.getDescription().equals(existFoodItem.getDescription())) {
            existFoodItem.setDescription(foodItemDTO.getDescription());
            foodItemWasChanged = true;
        }

        if (foodItemDTO.getPrice() != null && !foodItemDTO.getPrice().equals(existFoodItem.getPrice())) {
            existFoodItem.setPrice(foodItemDTO.getPrice());
            foodItemWasChanged = true;
        }
        if (foodItemWasChanged) {
            existFoodItem = foodItemGateway.saveFoodItem(existFoodItem);
            return existFoodItem;
        } else {
            throw new FoodItemException("Não identificada mudança na descrição ou no preço do item de alimentação, por favor revisar dados da atualização.", 400);
        }

    }
}
