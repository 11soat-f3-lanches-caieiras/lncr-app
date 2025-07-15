package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.commons.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

public class GetFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;

    public GetFoodItemImageUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public FoodItemImage getById(Integer foodItemImageId) {
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
