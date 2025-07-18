package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.exceptions.FoodItemException;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;

public class UpdateFoodItemUseCase {

    private final FoodItemGateway foodItemGateway;

    public UpdateFoodItemUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public FoodItem partialUpdateById(Integer foodItemId, FoodItemDTO foodItemDTO) {
        Logger.info("Iniciando atualização parcial do item de alimentação com id: " + foodItemId);
        FoodItem existFoodItem = foodItemGateway.getFoodItemById(foodItemId);
        if (existFoodItem == null) {
            throw new FoodItemException("Não encontrado Item de alimentação com Id: " + foodItemId, 404);
        }
        boolean foodItemWasChanged = false;

        if (foodItemDTO.getDescription() != null && !foodItemDTO.getDescription().equals(existFoodItem.getDescription())) {
            Logger.debug("Atualizando descrição do item de alimentação com id: " + foodItemId);
            existFoodItem.setDescription(foodItemDTO.getDescription());
            foodItemWasChanged = true;
        }

        if (foodItemDTO.getPrice() != null && !foodItemDTO.getPrice().equals(existFoodItem.getPrice())) {
            Logger.debug("Atualizando preço do item de alimentação com id: " + foodItemId);
            existFoodItem.setPrice(foodItemDTO.getPrice());
            foodItemWasChanged = true;
        }
        if (foodItemWasChanged) {
            existFoodItem = foodItemGateway.saveFoodItem(existFoodItem);
            Logger.info("Item de alimentação com id: " + foodItemId + " atualizado com sucesso.");
            return existFoodItem;
        } else {
            throw new FoodItemException("Não identificada mudança na descrição ou no preço do item de alimentação, por favor revisar dados da atualização.", 400);
        }

    }
}
