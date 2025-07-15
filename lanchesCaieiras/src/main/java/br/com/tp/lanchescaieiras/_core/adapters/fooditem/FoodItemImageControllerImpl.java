package br.com.tp.lanchescaieiras._core.adapters.fooditem;

import br.com.tp.lanchescaieiras._core.applications.fooditem.CreateFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.applications.fooditem.DeleteFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.applications.fooditem.GetFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.applications.fooditem.UpdateFoodItemImageUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemImageController;
import br.com.tp.lanchescaieiras._core.commons.utils.FoodItemImageRules;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;


public class FoodItemImageControllerImpl implements FoodItemImageController {

    private final FoodItemGateway foodItemGateway;
    private final FoodItemMapper foodItemMapper;

    public FoodItemImageControllerImpl(FoodItemDatabase foodItemDatabase) {
        this.foodItemMapper = new FoodItemMapper();
        this.foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, this.foodItemMapper);
    }

    @Override
    public FoodItemImageDTO create(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {

        FoodItemImage newFoodItemImage = new CreateFoodItemImageUseCase(foodItemGateway, foodItemImageRules).execute(foodItemId, foodItemImageDTO);
        return new FoodItemImagePresenter(foodItemMapper).created(newFoodItemImage, foodItemImageRules.getImageLocation());
    }

    @Override
    public FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDatabase foodItemDatabase, String imageLocationPrefix) {
        FoodItemImage foodItemImage = new GetFoodItemImageUseCase(foodItemGateway).getById(foodItemImageId);
        return new FoodItemImagePresenter(foodItemMapper).getById(foodItemImage, imageLocationPrefix);
    }

    @Override
    public FoodItemImageDTO updateImageById(Integer foodItemImageId, FoodItemImageDTO foodItemImageDTO, FoodItemDatabase foodItemDatabase, FoodItemImageRules foodItemImageRules) {
        FoodItemImage updateFoodItemImage = new UpdateFoodItemImageUseCase(foodItemGateway).updateImageById(foodItemImageId,foodItemImageDTO,foodItemImageRules);
        return new FoodItemImagePresenter(foodItemMapper).updateById(updateFoodItemImage,foodItemImageRules.getImageLocation());
    }

    @Override
    public void deleteImageById(Integer foodItemImageId, FoodItemDatabase foodItemDatabase) {
        new DeleteFoodItemImageUseCase(foodItemGateway).deleteById(foodItemImageId);
    }


}
