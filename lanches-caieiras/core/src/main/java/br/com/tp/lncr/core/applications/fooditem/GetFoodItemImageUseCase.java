package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.exceptions.FoodItemException;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;

public class GetFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;

    public GetFoodItemImageUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public FoodItemImage getById(Integer foodItemImageId) {
        Logger.info("Iniciando busca de imagem com id: " + foodItemImageId);
        FoodItemImage foodItemImage = foodItemGateway.getFoodItemImageById(foodItemImageId);
        if (foodItemImage == null) {
            throw new FoodItemException("Não encontrada imagem com id: " + foodItemImageId, 404);
        }

        if (foodItemImage.get_data() == null) {
            throw new FoodItemException("Não encontrado arquivo " + foodItemImage.getFileName() + " no sistema de arquivo.", 500);
        }
        return foodItemImage;
    }
}
