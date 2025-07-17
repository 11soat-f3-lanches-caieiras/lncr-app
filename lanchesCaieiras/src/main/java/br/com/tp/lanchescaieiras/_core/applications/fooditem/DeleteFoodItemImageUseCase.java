package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.commons.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.Logger;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

import java.util.List;

public class DeleteFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;

    public DeleteFoodItemImageUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public void deleteImagesByFoodItemId(Integer foodItemId) {
        Logger.info("Iniciando exclusão de imagens do item de alimentação com id: " + foodItemId);
        List<FoodItemImage> foodItemImages = this.foodItemGateway.getAllImagesByFoodItemId(foodItemId, false);
        if (foodItemImages.isEmpty()) {
            throw new FoodItemException("Não encontrada imagens para o item de alimentação com id: " + foodItemId, 404);
        }
        this.foodItemGateway.deleteImagesByFoodItemId(foodItemId);
        Logger.info("Imagens do item de alimentação com id: " + foodItemId + " excluídas com sucesso.");
    }


    public void deleteById(Integer foodItemImageId) {
        Logger.info("Iniciando exclusão de imagem com id: " + foodItemImageId);
        FoodItemImage foodItemImage = this.foodItemGateway.getFoodItemImageById(foodItemImageId);
        if (foodItemImage == null) {
            throw new FoodItemException("Não encontrada imagen id: " + foodItemImageId, 404);
        }
        this.foodItemGateway.deleteFoodItemImage(foodItemImage);
        Logger.info("Imagem com id: " + foodItemImageId + " excluída com sucesso.");
    }
}
