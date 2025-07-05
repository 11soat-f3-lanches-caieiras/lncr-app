package br.com.tp.lanchescaieiras._core.adapters.fooditem;

import br.com.tp.lanchescaieiras._core.applications.fooditem.*;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;

import java.util.List;


public class FoodItemControllerImpl implements FoodItemController {

    private final FoodItemMapper foodItemMapper = new FoodItemMapper();

    @Override
    public FoodItemDTO create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        FoodItem newfoodItem = new CreateFoodItemUseCase(foodItemGateway, foodItemImageRules).execute(foodItemDTO);
        return new FoodItemPresenter(foodItemMapper).created(newfoodItem, foodItemImageRules.getImageLocation());
    }

    @Override
    public List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        List<FoodItem> foodItemList = new GetFoodItemUseCase(foodItemGateway).getAll(_limit, category, includeImages);
        return new FoodItemPresenter(foodItemMapper).getAll(foodItemList, foodItemImageRules.getImageLocation());
    }

    @Override
    public FoodItemDTO getById(Integer foodItemId, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        FoodItem foodItem = new GetFoodItemUseCase(foodItemGateway).getById(foodItemId, includeImages);
        return new FoodItemPresenter(foodItemMapper).getById(foodItem, foodItemImageRules.getImageLocation());
    }

    @Override
    public FoodItemDTO partialUpdateById(Integer foodItemId, FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        FoodItem foodItem = new UpdateFoodItemUseCase(foodItemGateway).partialUpdateById(foodItemId, foodItemDTO);
        return new FoodItemPresenter(foodItemMapper).patialUpdateById(foodItem, foodItemImageRules.getImageLocation());
    }

    @Override
    public void deleteById(Integer foodItemId, FoodItemDatabase foodItemDatabase) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        new DeleteFoodItemUseCase(foodItemGateway).execute(foodItemId, foodItemGateway);
    }

    @Override
    public FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        FoodItemImage foodItemImage = new CreateFoodItemImageUseCase(foodItemGateway, foodItemImageRules).execute(foodItemId, foodItemImageDTO);
        return new FoodItemImagePresenter(foodItemMapper).created(foodItemImage, foodItemImageRules.getImageLocation());
    }

    @Override
    public List<FoodItemImageDTO> getFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        List<FoodItemImage> foodItemImageList = new GetFoodItemUseCase(foodItemGateway).getAllImages(foodItemId, includeData, foodItemGateway, foodItemMapper);
        return new FoodItemImagePresenter(foodItemMapper).getAllImagesByFoodItemId(foodItemImageList, foodItemImageRules.getImageLocation(), includeData);
    }

    @Override
    public void deleteImagesByFoodItemId(Integer foodItemId, FoodItemDatabase foodItemDatabase) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        new DeleteFoodItemImageUseCase(foodItemGateway).deleteImagesByFoodItemId(foodItemId);
    }
}
