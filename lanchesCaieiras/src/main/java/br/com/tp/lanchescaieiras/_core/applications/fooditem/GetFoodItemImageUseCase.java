package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.adapters.fooditem.FoodItemMapper;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

public class GetFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;
    private final FoodItemMapper foodItemMapper;

    public GetFoodItemImageUseCase(FoodItemGateway foodItemGateway, FoodItemMapper foodItemMapper) {
        this.foodItemGateway = foodItemGateway;
        this.foodItemMapper = foodItemMapper;
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
