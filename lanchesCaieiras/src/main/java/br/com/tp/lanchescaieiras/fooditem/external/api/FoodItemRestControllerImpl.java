package br.com.tp.lanchescaieiras.fooditem.external.api;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.foodItem.FoodItemRestController;
import br.com.tp.lanchescaieiras.commons.utils.ResponseEntityUtil;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemControllerImpl;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodItems")
public class FoodItemRestControllerImpl implements FoodItemRestController {

    private final FoodItemConfig foodItemConfig;
    private final FoodItemControllerImpl foodItemController;
    private final JpaFoodItemPostgresDatabaseImpl jpaFoodItemDatabase;
    private final JpaFoodItemImagePostgresDatabaseImpl jpaFoodItemImageDatabase;
    private final FoodItemImageStorageImpl foodItemImageStorage;


    public FoodItemRestControllerImpl(FoodItemConfig foodItemConfig, FoodItemControllerImpl foodItemController, JpaFoodItemPostgresDatabaseImpl jpaFoodItemDatabase, JpaFoodItemImagePostgresDatabaseImpl jpaFoodItemImageDatabase, FoodItemImageStorageImpl foodItemImageStorage) {
        this.foodItemConfig = foodItemConfig;
        this.foodItemController = foodItemController;
        this.jpaFoodItemDatabase = jpaFoodItemDatabase;
        this.jpaFoodItemImageDatabase = jpaFoodItemImageDatabase;
        this.foodItemImageStorage = foodItemImageStorage;
    }

    @Override
    @PostMapping
    public ResponseEntity<Response<FoodItemDTO>> createFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        foodItemDTO = this.foodItemController.create(foodItemDTO, foodItemDatabase, this.foodItemConfig);
        return ResponseEntityUtil.created(foodItemDTO,foodItemConfig.getLocationPrefix()+ "/" + foodItemDTO.getId());
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseList<FoodItemDTO>> getAllFoodItems(@RequestParam(name = "_limit", required = false) Integer _limit,
                                                                     @RequestParam(name = "category", required = false) String category,
                                                                     @RequestParam(name = "includeImages", required = false, defaultValue = "false") Boolean includeImages) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        List<FoodItemDTO> getAllFoodItemsList = this.foodItemController.getAll(_limit, category, includeImages, foodItemDatabase, this.foodItemConfig);
        return ResponseEntityUtil.listOK(getAllFoodItemsList);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response<FoodItemDTO>> getFoodItemById(@PathVariable(name = "id") Integer foodItemId,
                                                                 @RequestParam(name = "includeImages", required = false, defaultValue = "false") Boolean includeImages) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        FoodItemDTO foodItemDTO = this.foodItemController.getById(foodItemId,includeImages, foodItemDatabase, this.foodItemConfig);
        return ResponseEntityUtil.OK(foodItemDTO);

    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Response<FoodItemDTO>> partialUpdateFoodItemById(@PathVariable(name = "id") Integer foodItemId, @RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        foodItemDTO = this.foodItemController.partialUpdateById(foodItemId, foodItemDTO, foodItemDatabase, this.foodItemConfig);
        return ResponseEntityUtil.OK(foodItemDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<FoodItemDTO>> deleteFoodItemById(@PathVariable(name = "id") Integer foodItemId) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        this.foodItemController.deleteById(foodItemId,foodItemDatabase,foodItemConfig);
        return ResponseEntityUtil.OK(null);
    }


    @Override
    @PostMapping("/{foodItemId}/image")
    public ResponseEntity<Response<FoodItemImageDTO>> createFoodItemImage(@PathVariable("foodItemId") Integer foodItemId,
                                                                          @RequestBody FoodItemImageDTO foodItemImageDTO) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        foodItemImageDTO = this.foodItemController.create(foodItemId, foodItemImageDTO, foodItemDatabase, this.foodItemConfig);
        return ResponseEntityUtil.OK(foodItemImageDTO);
    }

    @Override
    @GetMapping("/{foodItemId}/images")
    public ResponseEntity<ResponseList<FoodItemImageDTO>> getFoodItemImagesByFoodItemId(@PathVariable Integer foodItemId,
                                                                                       @RequestParam(name="includeData",required = false, defaultValue = "false") Boolean includeData) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        List<FoodItemImageDTO> foodItemImageDTOList = this.foodItemController.getFoodItemImagesByFoodItemId(foodItemId, includeData, foodItemDatabase, foodItemConfig);
        return ResponseEntityUtil.listOK(foodItemImageDTOList);

    }


    @Override
    @DeleteMapping("/{foodItemId}/images")
    public ResponseEntity<Response<FoodItemImageDTO>> deleteFoodItemImageByFoodItemId(@PathVariable(name = "foodItemId") Integer foodItemId, FoodItemImageDTO foodItemImageDTO) {
        return null;
    }


}
