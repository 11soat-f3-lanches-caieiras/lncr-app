package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemController;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemDatabase;
import br.com.tp.lanchescaieiras.fooditem.application.*;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;

import java.util.List;


public class FoodItemControllerImpl implements FoodItemController {

    @Override
    public FoodItemDTO create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper){
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        foodItemDTO = new CreateFoodItemUseCase().execute(foodItemDTO, foodItemGateway, foodItemConfig, foodItemMapper);
        return new FoodItemPresenter().created(foodItemDTO, foodItemConfig);
    }

    @Override
    public List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        List<FoodItemDTO> foodItemListDTO = new GetFoodItemUseCase().getAll(_limit, category, includeImages, foodItemGateway, foodItemMapper);
        return new FoodItemPresenter().getAll(foodItemListDTO, foodItemConfig);
    }

    @Override
    public FoodItemDTO getById(Integer foodItemId, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        FoodItemDTO foodItemDTO =  new GetFoodItemUseCase().getById(foodItemId, includeImages,foodItemGateway,foodItemMapper);
        return new FoodItemPresenter().getById(foodItemDTO,foodItemConfig);
    }

    @Override
    public FoodItemDTO partialUpdateById(Integer foodItemId, FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        foodItemDTO = new UpdateFoodItemUseCase().partialUpdateById(foodItemId,foodItemDTO,foodItemGateway);
        return new FoodItemPresenter().patialUpdateById(foodItemDTO, foodItemConfig);
    }

    @Override
    public void deleteById(Integer foodItemId, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        new DeleteFoodItemUseCase().execute(foodItemId,foodItemGateway);
    }

    @Override
    public FoodItemImageDTO create(FoodItemImageDTO foodItemImageDTO, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        foodItemImageDTO = new CreateFoodItemImageUseCase().execute(foodItemImageDTO,foodItemConfig.getMaxImages(), foodItemGateway, foodItemMapper, foodItemConfig);
        return new FoodItemImagePresenter().created(foodItemImageDTO,foodItemConfig);
    }

    private FoodItemGatewayImpl createGateway(FoodItemDatabase foodItemDatabase, FoodItemMapper foodItemMapper){
        return new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
    }

    @Override
    public FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase,foodItemMapper);
        FoodItemImageDTO foodItemImageDTO = new GetFoodItemImageUseCase().getById(foodItemImageId,foodItemGateway,foodItemMapper);
        return new FoodItemImagePresenter().getById(foodItemImageDTO,foodItemConfig);

    }
}
