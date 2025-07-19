package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.exceptions.FoodItemException;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;

public class UpdateFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;

    public UpdateFoodItemImageUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public FoodItemImage updateImageById(Integer foodItemImageId, FoodItemImageDTO foodItemImageDTO, FoodItemImageRules foodItemImageRules) {
        Logger.info("Iniciando atualização de imagem com id: " + foodItemImageId);
        FoodItemImage existFoodItemImage = foodItemGateway.getFoodItemImageById(foodItemImageId);

        if (existFoodItemImage == null){
            throw new FoodItemException("Não encontrada imagem com o id: " + foodItemImageId,404);
        }
        foodItemImageDTO.setId(existFoodItemImage.getId());
        foodItemImageDTO.setFoodItemId(existFoodItemImage.getFoodItemId());
        FoodItemImage newFoodItemImage = foodItemGateway.createFoodItem(new FoodItemImage(foodItemImageDTO,foodItemImageRules));

        if (!existFoodItemImage.getFileName().equals(newFoodItemImage.getFileName())){
            Logger.debug("Arquivo de imagem alterado, removendo arquivo antigo: " + existFoodItemImage.getFileName());
            foodItemGateway.deleteImageFile(existFoodItemImage.getFileName());
        }
        Logger.info("Imagem com id: " + foodItemImageId + " atualizada com sucesso.");
        return newFoodItemImage;
    }
}
