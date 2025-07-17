package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.commons.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.Logger;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;

public class DeleteFoodItemUseCase {

    private final FoodItemGateway foodItemGateway;

    public DeleteFoodItemUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public void execute(Integer foodItemId) {
        Logger.info("Iniciando exclusão do item de alimentação com id: " + foodItemId);
        FoodItem foodItem = foodItemGateway.getFoodItemById(foodItemId, true);
        if (foodItem == null) {
            throw new FoodItemException("Não encontrado item de alimentação com id: " + foodItemId, 404);
        }
        foodItemGateway.deleteFoodItemImage(foodItem);
        Logger.info("Item de alimentação com id: " + foodItemId + " excluído com sucesso.");
    }
}
