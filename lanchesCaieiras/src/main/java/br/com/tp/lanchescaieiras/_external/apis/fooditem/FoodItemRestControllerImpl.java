package br.com.tp.lanchescaieiras._external.apis.fooditem;

import br.com.tp.lanchescaieiras._core.adapters.fooditem.FoodItemControllerImpl;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityUtil;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;
import br.com.tp.lanchescaieiras._external.commons.model.Response;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseList;
import br.com.tp.lanchescaieiras._external.configs.FoodItemConfig;
import br.com.tp.lanchescaieiras._external.dataproxy.FoodItemDataProxy;
import br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras._external.datasources.storage.fooditem.FoodItemImageStorageImpl;
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
        foodItemDTO = this.foodItemController.create(foodItemDTO, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityUtil.created(foodItemDTO, foodItemConfig.getLocationPrefix() + "/" + foodItemDTO.getId());
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseList<FoodItemDTO>> getAllFoodItems(@RequestParam(name = "_limit", required = false) Integer _limit,
                                                                     @RequestParam(name = "category", required = false) String category,
                                                                     @RequestParam(name = "includeImages", required = false, defaultValue = "false") Boolean includeImages) {
        List<FoodItemDTO> getAllFoodItemsList = this.foodItemController.getAll(_limit, category, includeImages, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityUtil.listOK(getAllFoodItemsList);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response<FoodItemDTO>> getFoodItemById(@PathVariable(name = "id") Integer foodItemId,
                                                                 @RequestParam(name = "includeImages", required = false, defaultValue = "false") Boolean includeImages) {
        FoodItemDTO foodItemDTO = this.foodItemController.getById(foodItemId, includeImages, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityUtil.OK(foodItemDTO);

    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Response<FoodItemDTO>> partialUpdateFoodItemById(@PathVariable(name = "id") Integer foodItemId, @RequestBody FoodItemDTO foodItemDTO) {
        foodItemDTO = this.foodItemController.partialUpdateById(foodItemId, foodItemDTO, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityUtil.OK(foodItemDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<FoodItemDTO>> deleteFoodItemById(@PathVariable(name = "id") Integer foodItemId) {
        this.foodItemController.deleteById(foodItemId, newFoodItemDatabase());
        return ResponseEntityUtil.OK(null);
    }


    @Override
    @PostMapping("/{foodItemId}/images")
    public ResponseEntity<Response<FoodItemImageDTO>> createFoodItemImage(@PathVariable("foodItemId") Integer foodItemId,
                                                                          @RequestBody FoodItemImageDTO foodItemImageDTO) {
        foodItemImageDTO = this.foodItemController.create(foodItemId, foodItemImageDTO, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityUtil.OK(foodItemImageDTO);
    }

    @Override
    @GetMapping("/{foodItemId}/images")
    public ResponseEntity<ResponseList<FoodItemImageDTO>> getFoodItemImagesByFoodItemId(@PathVariable Integer foodItemId,
                                                                                        @RequestParam(name = "includeData", required = false, defaultValue = "false") Boolean includeData) {
        List<FoodItemImageDTO> foodItemImageDTOList = this.foodItemController.getFoodItemImagesByFoodItemId(foodItemId, includeData, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityUtil.listOK(foodItemImageDTOList);
    }


    @Override
    @DeleteMapping("/{foodItemId}/images")
    public ResponseEntity<Response<FoodItemImageDTO>> deleteFoodItemImageByFoodItemId(@PathVariable(name = "foodItemId") Integer foodItemId) {
        this.foodItemController.deleteImagesByFoodItemId(foodItemId, newFoodItemDatabase());
        return ResponseEntityUtil.OK(null);
    }

    private FoodItemImageRules newFoodItemImageRule() {
        return new FoodItemImageRules(foodItemConfig.getImage().getLocationPrefix(), foodItemConfig.getMaxImages(), foodItemConfig.getImage().getMaxSize(), foodItemConfig.getImage().getAllowedExtensions());
    }

    private FoodItemDatabase newFoodItemDatabase() {
        return new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
    }

}
