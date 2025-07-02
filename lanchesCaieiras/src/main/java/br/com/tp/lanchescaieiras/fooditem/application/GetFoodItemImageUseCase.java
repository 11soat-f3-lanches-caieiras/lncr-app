package br.com.tp.lanchescaieiras.fooditem.application;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemGatewayImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;

public class GetFoodItemImageUseCase {
    public FoodItemImageDTO getById(Integer foodItemImageId, FoodItemGatewayImpl foodItemGateway, FoodItemMapper foodItemMapper) {
        FoodItemImage foodItemImage = foodItemGateway.getFoodItemImageById(foodItemImageId);
        if (foodItemImage == null){
            throw new FoodItemException("Não encontrada imagem com id: " + foodItemImageId,404);
        }
        return foodItemMapper.imageDomainToDto(foodItemImage);
    }
}
