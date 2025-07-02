package br.com.tp.lanchescaieiras.fooditem.application;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemGatewayImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemMapper;

public class GetFoodItemImageUseCase {
    public FoodItemImageDTO getById(Integer foodItemImageId, FoodItemGatewayImpl foodItemGateway, FoodItemMapper foodItemMapper) {
        FoodItemImage foodItemImage = foodItemGateway.getFoodItemImageById(foodItemImageId);
        if (foodItemImage == null){
            throw new FoodItemException("Não encontrada imagem com id: " + foodItemImageId,404);
        }

        if (foodItemImage.get_data() == null){
            throw new FoodItemException("Não encontrado arquivo " + foodItemImage.getFileName() + " no sistema de arquivo.",500);
        }

        return foodItemMapper.imageDomainToDto(foodItemImage);
    }
}
