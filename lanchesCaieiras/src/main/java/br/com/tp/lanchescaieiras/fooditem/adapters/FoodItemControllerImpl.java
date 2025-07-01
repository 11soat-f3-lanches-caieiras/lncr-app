package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemController;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemDatabase;
import br.com.tp.lanchescaieiras.fooditem.application.CreateFoodItemUseCase;
import br.com.tp.lanchescaieiras.fooditem.application.GetFoodItemUseCase;
import br.com.tp.lanchescaieiras.fooditem.application.UpdateFoodItemUseCase;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;


public class FoodItemControllerImpl implements FoodItemController {

    @Override
    public ResponseEntity<Response<FoodItemDTO>> create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        foodItemDTO = new CreateFoodItemUseCase().execute(foodItemDTO, foodItemGateway, foodItemConfig, foodItemMapper);
        return new FoodItemPresenter().created(foodItemDTO, foodItemConfig);
    }

    @Override
    public ResponseEntity<ResponseList<FoodItemDTO>> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        List<FoodItemDTO> foodItemListDTO = new GetFoodItemUseCase().getAll(_limit, category, includeImages, foodItemGateway, foodItemMapper);
        return new FoodItemPresenter().getAll(foodItemListDTO, foodItemConfig);
    }

    @Override
    public ResponseEntity<Response<FoodItemDTO>> getById(Integer foodItemId, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        FoodItemDTO foodItemDTO =  new GetFoodItemUseCase().getById(foodItemId, includeImages,foodItemGateway,foodItemMapper);
        return new FoodItemPresenter().getById(foodItemDTO,foodItemConfig);
    }

    @Override
    public ResponseEntity<Response<FoodItemDTO>> partialUpdateById(Integer foodItemId, FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = createGateway(foodItemDatabase, foodItemMapper);
        foodItemDTO = new UpdateFoodItemUseCase().partialUpdateById(foodItemId,foodItemDTO,foodItemGateway);
        return new FoodItemPresenter().patialUpdateById(foodItemDTO, foodItemConfig);
    }

    private FoodItemGatewayImpl createGateway(FoodItemDatabase foodItemDatabase, FoodItemMapper foodItemMapper){
        return new FoodItemGatewayImpl(foodItemDatabase,foodItemMapper);
    }
}
