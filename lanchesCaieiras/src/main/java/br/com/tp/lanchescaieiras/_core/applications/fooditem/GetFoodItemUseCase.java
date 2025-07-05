package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.adapters.fooditem.FoodItemGatewayImpl;
import br.com.tp.lanchescaieiras._core.adapters.fooditem.FoodItemMapper;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

import java.util.List;

public class GetFoodItemUseCase {

    private final FoodItemGateway foodItemGateway;

    public GetFoodItemUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public List<FoodItem> getAll(Integer _limit, String category, Boolean includeImages) {
        if (_limit == null) {
            _limit = 10; // Default limit
        } else {
            if (_limit < 1 || _limit > 50) {
                throw new FoodItemException("Limite deve estar entre 1 e 50", 400);
            }
        }

        List<FoodItem> foodItemList = foodItemGateway.getAllFoodItems(_limit, category, includeImages);
        return foodItemList;
    }

    public FoodItem getById(Integer foodItemId, Boolean includeImages) {
        FoodItem foodItem = foodItemGateway.getFoodItemById(foodItemId, includeImages);
        if (foodItem == null) {
            throw new FoodItemException("Item de Alimentação não encontrado com id: " + foodItemId, 404);
        }
        return foodItem;
    }

    public List<FoodItemImage> getAllImages(Integer foodItemId, Boolean includeData, FoodItemGatewayImpl foodItemGateway, FoodItemMapper foodItemMapper) {
        List<FoodItemImage> foodItemList = foodItemGateway.getAllImagesByFoodItemId(foodItemId, includeData);
        if (foodItemList.isEmpty()) {
            throw new FoodItemException("Não encontrada imagens para o item de alimentação com id: " + foodItemId, 404);
        }
        return foodItemList;

    }
}
