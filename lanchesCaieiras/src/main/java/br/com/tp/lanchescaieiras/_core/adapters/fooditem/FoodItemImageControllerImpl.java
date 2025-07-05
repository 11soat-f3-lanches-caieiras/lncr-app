package br.com.tp.lanchescaieiras._core.adapters.fooditem;

import br.com.tp.lanchescaieiras._core.applications.fooditem.CreateFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.applications.fooditem.GetFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemImageController;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;
import br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem.FoodItemDataProxy;


public class FoodItemImageControllerImpl implements FoodItemImageController {

    private final FoodItemMapper foodItemMapper = new FoodItemMapper();

    @Override
    public FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDataProxy foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        FoodItemImage newFoodItemImage = new CreateFoodItemImageUseCase(foodItemGateway, foodItemImageRules).execute(foodItemId, foodItemImageDTO);
        return new FoodItemImagePresenter(foodItemMapper).created(newFoodItemImage, foodItemImageRules.getImageLocation());
    }

    @Override
    public FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDataProxy foodItemDatabase, String imageLocationPrefix) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        FoodItemImage foodItemImage = new GetFoodItemImageUseCase(foodItemGateway, foodItemMapper).getById(foodItemImageId);
        return new FoodItemImagePresenter(foodItemMapper).getById(foodItemImage, imageLocationPrefix);
    }

}
