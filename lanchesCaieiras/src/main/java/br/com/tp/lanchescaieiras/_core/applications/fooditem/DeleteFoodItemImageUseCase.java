package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

import java.util.List;

public class DeleteFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;

    public DeleteFoodItemImageUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public void deleteImagesByFoodItemId(Integer foodItemId) {
        List<FoodItemImage> foodItemImages = this.foodItemGateway.getAllImagesByFoodItemId(foodItemId, false);
        if (foodItemImages.isEmpty()) {
            throw new FoodItemException("Não encontrada imagens para o item de alimentação com id: " + foodItemId, 404);
        }
        this.foodItemGateway.deleteImagesByFoodItemId(foodItemId);
    }
}
