package br.com.tp.lanchescaieiras.fooditem.application;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemGatewayImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;

public class CreateFoodItemImageUseCase {
    public FoodItemImageDTO execute(FoodItemImageDTO foodItemImageDTO, Integer maxImages, FoodItemGatewayImpl foodItemGateway, FoodItemMapper foodItemMapper, FoodItemConfig foodItemConfig) {
        Integer numberOfImages = foodItemGateway.getCountImagesByFoodItemId(foodItemImageDTO.getFoodItemId());

        if(numberOfImages >= maxImages){
            throw new FoodItemException("O item de alimentação com id " +foodItemImageDTO.getFoodItemId() +" já possui 5 imagens. Subistitua uma imagem já existente",409);
        }

        FoodItemImage foodItemImage = foodItemMapper.imageDtoToDomain(foodItemImageDTO);
        foodItemImage.validateImage(foodItemConfig);

        if (foodItemImage.getImageError()!= null) {
            throw new FoodItemException(foodItemImage.getImageError(), 404);
        }

        foodItemImage = setImageInfo(foodItemImage,numberOfImages);
        foodItemGateway.create(foodItemImage);

        return foodItemMapper.imageDomainToDto(foodItemImage);
    }

    private FoodItemImage setImageInfo(FoodItemImage foodItemImage, Integer numberOfImages){
        int nextId = numberOfImages + 1 ;
        String imageId = foodItemImage.getFoodItemId().toString() + nextId;
        foodItemImage.setId(Integer.parseInt(imageId));
        foodItemImage.setFileName(imageId + "." +foodItemImage.getFileExtension());
        return foodItemImage;
    }

}
