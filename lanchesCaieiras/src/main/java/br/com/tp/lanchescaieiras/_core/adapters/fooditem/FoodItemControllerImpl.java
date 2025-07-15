package br.com.tp.lanchescaieiras._core.adapters.fooditem;

import br.com.tp.lanchescaieiras._core.applications.fooditem.*;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.commons.utils.FoodItemImageRules;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

import java.util.List;


public class FoodItemControllerImpl implements FoodItemController {

    private final FoodItemGateway foodItemGateway;
    private final FoodItemMapper foodItemMapper;

    public FoodItemControllerImpl(FoodItemDatabase foodItemDatabase) {
        this.foodItemMapper = new FoodItemMapper();
        this.foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, this.foodItemMapper);
    }

    @Override
    public FoodItemDTO create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItem newfoodItem = new CreateFoodItemUseCase(foodItemGateway, foodItemImageRules).execute(foodItemDTO);
        return new FoodItemPresenter(foodItemMapper).created(newfoodItem, foodItemImageRules.getImageLocation());
    }

    @Override
    public List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        List<FoodItem> foodItemList = new GetFoodItemUseCase(foodItemGateway).getAll(_limit, category, includeImages);
        return new FoodItemPresenter(foodItemMapper).getAll(foodItemList, foodItemImageRules.getImageLocation());
    }

    @Override
    public FoodItemDTO getById(Integer foodItemId, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItem foodItem = new GetFoodItemUseCase(foodItemGateway).getById(foodItemId, includeImages);
        return new FoodItemPresenter(foodItemMapper).getById(foodItem, foodItemImageRules.getImageLocation());
    }

    @Override
    public List<FoodItemDTO> getByIdList(List<Integer> foodItemIdList, FoodItemDatabase foodItemDatabase) {
        List<FoodItem> foodItemlist = new GetFoodItemUseCase(foodItemGateway).getByIdList(foodItemIdList);
        return new FoodItemPresenter(foodItemMapper).getByIdList(foodItemlist);
    }


    @Override
    public FoodItemDTO partialUpdateById(Integer foodItemId, FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItem foodItem = new UpdateFoodItemUseCase(foodItemGateway).partialUpdateById(foodItemId, foodItemDTO);
        return new FoodItemPresenter(foodItemMapper).patialUpdateById(foodItem, foodItemImageRules.getImageLocation());
    }

    @Override
    public void deleteById(Integer foodItemId, FoodItemDatabase foodItemDatabase) {
        new DeleteFoodItemUseCase(foodItemGateway).execute(foodItemId);
    }

    @Override
    public FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemImage foodItemImage = new CreateFoodItemImageUseCase(foodItemGateway, foodItemImageRules).execute(foodItemId, foodItemImageDTO);
        return new FoodItemImagePresenter(foodItemMapper).created(foodItemImage, foodItemImageRules.getImageLocation());
    }

    @Override
    public List<FoodItemImageDTO> getFoodItemImagesByFoodItemId(Integer foodItemId, Boolean includeData, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        List<FoodItemImage> foodItemImageList = new GetFoodItemUseCase(foodItemGateway).getAllImages(foodItemId, includeData);
        return new FoodItemImagePresenter(foodItemMapper).getAllImagesByFoodItemId(foodItemImageList, foodItemImageRules.getImageLocation(), includeData);
    }

    @Override
    public void deleteImagesByFoodItemId(Integer foodItemId, FoodItemDatabase foodItemDatabase) {
        new DeleteFoodItemImageUseCase(foodItemGateway).deleteImagesByFoodItemId(foodItemId);
    }
}
