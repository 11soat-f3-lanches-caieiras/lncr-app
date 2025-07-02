package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.foodItem.FoodItemImageController;
import br.com.tp.lanchescaieiras.fooditem.application.*;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;


public class FoodItemImageControllerImpl implements FoodItemImageController {

    private final FoodItemMapper foodItemMapper= new FoodItemMapper();

    @Override
    public FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
        foodItemImageDTO = new CreateFoodItemImageUseCase().execute(foodItemId, foodItemImageDTO,foodItemConfig.getMaxImages(), foodItemGateway, foodItemMapper, foodItemConfig);
        return new FoodItemImagePresenter().created(foodItemImageDTO,foodItemConfig);
    }

    @Override
    public FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
        FoodItemImageDTO foodItemImageDTO = new GetFoodItemImageUseCase().getById(foodItemImageId,foodItemGateway,foodItemMapper);
        return new FoodItemImagePresenter().getById(foodItemImageDTO,foodItemConfig);

    }

}
