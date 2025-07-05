package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;

public class UpdateFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;

    public UpdateFoodItemImageUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public FoodItemImage updateImageById(Integer foodItemImageId, FoodItemImageDTO foodItemImageDTO, FoodItemImageRules foodItemImageRules) {
        FoodItemImage existFoodItemImage = foodItemGateway.getFoodItemImageById(foodItemImageId);

        if (existFoodItemImage == null){
            throw new FoodItemException("Não encontrada imagem com o id: " + foodItemImageId,404);
        }
        foodItemImageDTO.setId(existFoodItemImage.getId());
        foodItemImageDTO.setFoodItemId(existFoodItemImage.getFoodItemId());
        FoodItemImage newFoodItemImage = foodItemGateway.save(new FoodItemImage(foodItemImageDTO,foodItemImageRules));

        if (!existFoodItemImage.getFileName().equals(newFoodItemImage.getFileName())){
            foodItemGateway.deleteImageFile(existFoodItemImage.getFileName());
        }

        return newFoodItemImage;

    }
}
