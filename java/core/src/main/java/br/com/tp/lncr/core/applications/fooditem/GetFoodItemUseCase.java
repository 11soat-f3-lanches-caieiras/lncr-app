package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.exceptions.FoodItemException;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;

import java.util.List;

public class GetFoodItemUseCase {

    private final FoodItemGateway foodItemGateway;

    public GetFoodItemUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public List<FoodItem> getAll(Integer _limit, String category, Boolean includeImages) {
        Logger.info("Iniciando busca de todos os itens de alimentação com limite: " + _limit + ", categoria: " + category + ", incluir imagens: " + includeImages);
        if (_limit == null) {
            Logger.debug("Limite não informado, usando valor padrão de 10");
            _limit = 10; // Default limit
        } else {
            if (_limit < 1 || _limit > 50) {
                throw new FoodItemException("Limite deve estar entre 1 e 50", 400);
            }
        }

        List<FoodItem> foodItemList = foodItemGateway.getAllFoodItems(_limit, category, includeImages);
        Logger.info("Itens de alimentação encontrados: " + foodItemList.size());
        return foodItemList;
    }

    public FoodItem getById(Integer foodItemId, Boolean includeImages) {
        Logger.info("Iniciando busca de item de alimentação com id: " + foodItemId + ", incluir imagens: " + includeImages);
        FoodItem foodItem = foodItemGateway.getFoodItemById(foodItemId, includeImages);
        if (foodItem == null) {
            throw new FoodItemException("Item de Alimentação não encontrado com id: " + foodItemId, 404);
        }
        Logger.info("Item de alimentação encontrado: " + foodItem.getName());
        return foodItem;
    }

    public List<FoodItem> getByIdList(List<Integer> foodItemIds) {
        Logger.info("Iniciando busca de itens de alimentação com ids: " + foodItemIds);
        List<FoodItem> foodItemList = foodItemGateway.getFoodItemByIdList(foodItemIds);
        if (foodItemList == null) {
            throw new FoodItemException("Item de Alimentação não encontrado com ids: " + foodItemIds, 404);
        }
        Logger.info("Itens de alimentação encontrados: " + foodItemList.size());
        return foodItemList;
    }

    public List<FoodItemImage> getAllImages(Integer foodItemId, Boolean includeData) {
        List<FoodItemImage> foodItemList = foodItemGateway.getAllImagesByFoodItemId(foodItemId, includeData);
        if (foodItemList.isEmpty()) {
            throw new FoodItemException("Não encontrada imagens para o item de alimentação com id: " + foodItemId, 404);
        }
        return foodItemList;

    }
}
