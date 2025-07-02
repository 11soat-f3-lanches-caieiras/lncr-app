package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.foodItem.FoodItemController;
import br.com.tp.lanchescaieiras.commons.interfaces.foodItem.FoodItemDatabase;
import br.com.tp.lanchescaieiras.fooditem.application.*;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;

import java.util.List;


public class FoodItemControllerImpl implements FoodItemController {

    private final FoodItemMapper foodItemMapper= new FoodItemMapper();

    @Override
    public FoodItemDTO create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig){
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
        foodItemDTO = new CreateFoodItemUseCase().execute(foodItemDTO, foodItemGateway, foodItemConfig, foodItemMapper);
        return new FoodItemPresenter().created(foodItemDTO, foodItemConfig);
    }

    @Override
    public List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
        List<FoodItemDTO> foodItemListDTO = new GetFoodItemUseCase().getAll(_limit, category, includeImages, foodItemGateway, foodItemMapper);
        return new FoodItemPresenter().getAll(foodItemListDTO, foodItemConfig);
    }

    @Override
    public FoodItemDTO getById(Integer foodItemId, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
        FoodItemDTO foodItemDTO =  new GetFoodItemUseCase().getById(foodItemId, includeImages,foodItemGateway,foodItemMapper);
        return new FoodItemPresenter().getById(foodItemDTO,foodItemConfig);
    }

    @Override
    public FoodItemDTO partialUpdateById(Integer foodItemId, FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
        foodItemDTO = new UpdateFoodItemUseCase().partialUpdateById(foodItemId,foodItemDTO,foodItemGateway,foodItemMapper);
        return new FoodItemPresenter().patialUpdateById(foodItemDTO, foodItemConfig);
    }

    @Override
    public void deleteById(Integer foodItemId, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
        new DeleteFoodItemUseCase().execute(foodItemId,foodItemGateway);
    }

    @Override
    public FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
        foodItemImageDTO = new CreateFoodItemImageUseCase().execute(foodItemId, foodItemImageDTO,foodItemConfig.getMaxImages(), foodItemGateway, foodItemMapper, foodItemConfig);
        return new FoodItemImagePresenter().created(foodItemImageDTO,foodItemConfig);
    }

    @Override
    public List<FoodItemImageDTO> getFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
        List<FoodItemImageDTO> foodItemImageDTOList = new GetFoodItemUseCase().getAllImages(foodItemId,includeData,foodItemGateway,foodItemMapper);
        return new FoodItemImagePresenter().getAllImagesByFoodItemId(foodItemImageDTOList,foodItemConfig.getImage().getLocationPrefix(),includeData);
    }


}
