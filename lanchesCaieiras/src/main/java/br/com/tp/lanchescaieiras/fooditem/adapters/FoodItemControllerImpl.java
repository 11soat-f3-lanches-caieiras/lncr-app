package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemController;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemDatabase;
import br.com.tp.lanchescaieiras.fooditem.application.CreateFoodItemUseCase;
import br.com.tp.lanchescaieiras.fooditem.application.GetFoodItemUseCase;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;


public class FoodItemControllerImpl implements FoodItemController {

    @Override
    public ResponseEntity<Response<FoodItemDTO>> create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);

        CreateFoodItemUseCase createFoodItemUseCase = new CreateFoodItemUseCase();
        foodItemDTO = createFoodItemUseCase.execute(foodItemDTO, foodItemGateway, foodItemConfig, foodItemMapper);




        return new FoodItemPresenter().created(foodItemDTO, foodItemConfig);
    }

    @Override
    public ResponseEntity<ResponseList<FoodItemDTO>> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper) {
        FoodItemGatewayImpl foodItemGateway = new FoodItemGatewayImpl(foodItemDatabase, foodItemMapper);
        List<FoodItemDTO> foodItemListDTO = new GetFoodItemUseCase().getAll(_limit, category, includeImages, foodItemGateway, foodItemMapper);


        return new FoodItemPresenter().getAll(foodItemListDTO, foodItemConfig);
    }
}
