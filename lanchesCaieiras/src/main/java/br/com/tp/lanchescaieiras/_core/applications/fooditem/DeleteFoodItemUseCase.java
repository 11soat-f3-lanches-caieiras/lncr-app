package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;

public class DeleteFoodItemUseCase {

    private final FoodItemGateway foodItemGateway;

    public DeleteFoodItemUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public void execute(Integer foodItemId) {
        FoodItem foodItem = foodItemGateway.getFoodItemById(foodItemId, true);
        if (foodItem == null) {
            throw new FoodItemException("Não encontrado item de alimentação com id: " + foodItemId, 404);
        }
        foodItemGateway.delete(foodItem);

    }
}
