package br.com.tp.lanchescaieiras._external.apis.fooditem;

import br.com.tp.lanchescaieiras._core.adapters.fooditem.FoodItemControllerImpl;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseListModel;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
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
    public ResponseEntity<ResponseModel<FoodItemDTO>> createFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        foodItemDTO = this.foodItemController.create(foodItemDTO, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityModelUtil.created(foodItemDTO, foodItemConfig.getLocationPrefix() + "/" + foodItemDTO.getId());
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseListModel<FoodItemDTO>> getAllFoodItems(@RequestParam(name = "_limit", required = false) Integer _limit,
                                                                          @RequestParam(name = "category", required = false) String category,
                                                                          @RequestParam(name = "includeImages", required = false, defaultValue = "false") Boolean includeImages) {
        List<FoodItemDTO> getAllFoodItemsList = this.foodItemController.getAll(_limit, category, includeImages, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityModelUtil.listOK(getAllFoodItemsList);
    }


    @Override
    @GetMapping("/{foodItemId}")
    public ResponseEntity<ResponseModel<FoodItemDTO>> getFoodItemById(@PathVariable(name = "foodItemId") Integer foodItemId,
                                                                      @RequestParam(name = "includeImages", required = false, defaultValue = "false") Boolean includeImages) {
        FoodItemDTO foodItemDTO = this.foodItemController.getById(foodItemId, includeImages, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityModelUtil.OK(foodItemDTO);

    }
    @Override
    @GetMapping("/foodItemListIds/{foodItemIdList}")
    public ResponseEntity<ResponseListModel<FoodItemDTO>> getFoodItemByIdList(@PathVariable(name = "foodItemIdList") List<Integer> foodItemIdList) {
        List<FoodItemDTO> foodItemDTOList = this.foodItemController.getByIdList(foodItemIdList,newFoodItemDatabase());
        return ResponseEntityModelUtil.listOK(foodItemDTOList);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseModel<FoodItemDTO>> partialUpdateFoodItemById(@PathVariable(name = "id") Integer foodItemId, @RequestBody FoodItemDTO foodItemDTO) {
        foodItemDTO = this.foodItemController.partialUpdateById(foodItemId, foodItemDTO, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityModelUtil.OK(foodItemDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<FoodItemDTO>> deleteFoodItemById(@PathVariable(name = "id") Integer foodItemId) {
        this.foodItemController.deleteById(foodItemId, newFoodItemDatabase());
        return ResponseEntityModelUtil.OK(null);
    }


    @Override
    @PostMapping("/{foodItemId}/images")
    public ResponseEntity<ResponseModel<FoodItemImageDTO>> createFoodItemImage(@PathVariable("foodItemId") Integer foodItemId,
                                                                               @RequestBody FoodItemImageDTO foodItemImageDTO) {
        foodItemImageDTO = this.foodItemController.create(foodItemId, foodItemImageDTO, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityModelUtil.OK(foodItemImageDTO);
    }

    @Override
    @GetMapping("/{foodItemId}/images")
    public ResponseEntity<ResponseListModel<FoodItemImageDTO>> getFoodItemImagesByFoodItemId(@PathVariable Integer foodItemId,
                                                                                             @RequestParam(name = "includeData", required = false, defaultValue = "false") Boolean includeData) {
        List<FoodItemImageDTO> foodItemImageDTOList = this.foodItemController.getFoodItemImagesByFoodItemId(foodItemId, includeData, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityModelUtil.listOK(foodItemImageDTOList);
    }


    @Override
    @DeleteMapping("/{foodItemId}/images")
    public ResponseEntity<ResponseModel<FoodItemImageDTO>> deleteFoodItemImageByFoodItemId(@PathVariable(name = "foodItemId") Integer foodItemId) {
        this.foodItemController.deleteImagesByFoodItemId(foodItemId, newFoodItemDatabase());
        return ResponseEntityModelUtil.OK(null);
    }

    private FoodItemImageRules newFoodItemImageRule() {
        return new FoodItemImageRules(foodItemConfig.getImage().getLocationPrefix(), foodItemConfig.getMaxImages(), foodItemConfig.getImage().getMaxSize(), foodItemConfig.getImage().getAllowedExtensions());
    }

    private FoodItemDatabase newFoodItemDatabase() {
        return new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
    }

}
