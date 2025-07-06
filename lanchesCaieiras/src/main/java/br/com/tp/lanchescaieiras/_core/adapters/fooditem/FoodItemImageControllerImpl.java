package br.com.tp.lanchescaieiras._core.adapters.fooditem;

import br.com.tp.lanchescaieiras._core.applications.fooditem.CreateFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.applications.fooditem.DeleteFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.applications.fooditem.GetFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.applications.fooditem.UpdateFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemImageController;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;


public class FoodItemImageControllerImpl implements FoodItemImageController {

    private final FoodItemMapper foodItemMapper = new FoodItemMapper();

    @Override
    public FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        FoodItemImage newFoodItemImage = new CreateFoodItemImageUseCase(foodItemGateway, foodItemImageRules).execute(foodItemId, foodItemImageDTO);
        return new FoodItemImagePresenter(foodItemMapper).created(newFoodItemImage, foodItemImageRules.getImageLocation());
    }

    @Override
    public FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDatabase foodItemDatabase, String imageLocationPrefix) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        FoodItemImage foodItemImage = new GetFoodItemImageUseCase(foodItemGateway).getById(foodItemImageId);
        return new FoodItemImagePresenter(foodItemMapper).getById(foodItemImage, imageLocationPrefix);
    }

    @Override
    public FoodItemImageDTO updateImageById(Integer foodItemImageId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        FoodItemImage updateFoodItemImage = new UpdateFoodItemImageUseCase(foodItemGateway).updateImageById(foodItemImageId,foodItemImageDTO,foodItemImageRules);
        return new FoodItemImagePresenter(foodItemMapper).updateById(updateFoodItemImage,foodItemImageRules.getImageLocation());
    }

    @Override
    public void deleteImageById(Integer foodItemImageId, FoodItemDatabase foodItemDatabase) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        new DeleteFoodItemImageUseCase(foodItemGateway).deleteById(foodItemImageId);
    }


}
