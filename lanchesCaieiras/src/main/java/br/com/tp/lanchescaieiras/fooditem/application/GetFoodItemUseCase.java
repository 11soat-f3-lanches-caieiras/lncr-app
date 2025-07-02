package br.com.tp.lanchescaieiras.fooditem.application;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemGatewayImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemMapper;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;

import java.util.List;

public class GetFoodItemUseCase {
    public List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages,
                                    FoodItemGatewayImpl foodItemGateway,
                                    FoodItemMapper foodItemMapper) {
        if (_limit == null) {
            _limit = 10; // Default limit
        }else {
            if (_limit < 1 || _limit > 50) {
                throw new FoodItemException("Limite deve estar entre 1 e 50", 400);
            }
        }

        List<FoodItem> foodItemList = foodItemGateway.getAllFoodItems(_limit, category, includeImages, foodItemMapper);
        return foodItemList.stream()
                .map(foodItemMapper::domainToDto)
                .toList();
    }

    public FoodItemDTO getById(Integer foodItemId, Boolean includeImages, FoodItemGatewayImpl foodItemGateway, FoodItemMapper foodItemMapper) {
        FoodItem foodItem = foodItemGateway.getFoodItemById(foodItemId,includeImages);
        if (foodItem == null){
            throw new FoodItemException("Item de Alimentação não encontrado com id: "+ foodItemId,404);
        }
        return foodItemMapper.domainToDto(foodItem);
    }

    public List<FoodItemImageDTO> getAllImages(Integer foodItemId, Boolean includeData, FoodItemGatewayImpl foodItemGateway, FoodItemMapper foodItemMapper) {
        List<FoodItemImage> foodItemList = foodItemGateway.findAllFoodItemImagesByFoodItemId(foodItemId,includeData);
        if (foodItemList.isEmpty()){
            throw new FoodItemException("Não encontrada imagens para o item de alimentação com id: "+ foodItemId,404);
        }
        return foodItemMapper.imageDomainListToDtoList(foodItemList);

    }
}
