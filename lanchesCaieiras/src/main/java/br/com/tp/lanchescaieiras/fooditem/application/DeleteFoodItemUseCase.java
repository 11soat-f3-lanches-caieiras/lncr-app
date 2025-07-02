package br.com.tp.lanchescaieiras.fooditem.application;

import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemGatewayImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;

public class DeleteFoodItemUseCase {
    public void execute(Integer foodItemId, FoodItemGatewayImpl foodItemGateway) {
        FoodItem foodItem = foodItemGateway.getFoodItemById(foodItemId,true);
        if (foodItem == null) {
            throw new FoodItemException("Não encontrado item de alimentação com id: " + foodItemId,404);
        }
        foodItemGateway.delete(foodItem);

    }
}
