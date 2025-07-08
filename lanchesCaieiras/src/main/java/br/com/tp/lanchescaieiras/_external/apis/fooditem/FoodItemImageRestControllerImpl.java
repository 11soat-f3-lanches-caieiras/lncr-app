package br.com.tp.lanchescaieiras._external.apis.fooditem;

import br.com.tp.lanchescaieiras._core.adapters.fooditem.FoodItemImageControllerImpl;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemDatabase;
import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
import br.com.tp.lanchescaieiras._external.configs.FoodItemConfig;
import br.com.tp.lanchescaieiras._external.dataproxy.FoodItemDataProxy;
import br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras._external.datasources.storage.fooditem.FoodItemImageStorageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodItems/image")
public class FoodItemImageRestControllerImpl implements FoodItemImageRestController {

    private final FoodItemConfig foodItemConfig;
    private final FoodItemImageControllerImpl foodItemImageController;
    private final JpaFoodItemPostgresDatabaseImpl jpaFoodItemDatabase;
    private final JpaFoodItemImagePostgresDatabaseImpl jpaFoodItemImageDatabase;
    private final FoodItemImageStorageImpl foodItemImageStorage;


    public FoodItemImageRestControllerImpl(FoodItemConfig foodItemConfig, FoodItemImageControllerImpl foodItemImageController, JpaFoodItemPostgresDatabaseImpl jpaFoodItemDatabase, JpaFoodItemImagePostgresDatabaseImpl jpaFoodItemImageDatabase, FoodItemImageStorageImpl foodItemImageStorage) {
        this.foodItemConfig = foodItemConfig;
        this.foodItemImageController = foodItemImageController;
        this.jpaFoodItemDatabase = jpaFoodItemDatabase;
        this.jpaFoodItemImageDatabase = jpaFoodItemImageDatabase;
        this.foodItemImageStorage = foodItemImageStorage;
    }

    @Override
    @GetMapping("/{foodItemImageId}")
    public ResponseEntity<ResponseModel<FoodItemImageDTO>> getFoodItemImageById(@PathVariable(name = "foodItemImageId") Integer foodItemImageId) {
        FoodItemImageDTO foodItemImageDTO = this.foodItemImageController.getImageById(foodItemImageId, newFoodItemDatabase(), foodItemConfig.getImage().getLocationPrefix());
        return ResponseEntityModelUtil.OK(foodItemImageDTO);
    }

    @Override
    @PutMapping("/{foodItemImageId}")
    public ResponseEntity<ResponseModel<FoodItemImageDTO>> updateFoodItemImageById(@PathVariable(name = "foodItemImageId") Integer foodItemImageId, @RequestBody FoodItemImageDTO foodItemImageDTO) {
        foodItemImageDTO = this.foodItemImageController.updateImageById(foodItemImageId, foodItemImageDTO, newFoodItemDatabase(), newFoodItemImageRule());
        return ResponseEntityModelUtil.OK(foodItemImageDTO);
    }


    @Override
    @DeleteMapping("{foodItemImageId}")
    public ResponseEntity<ResponseModel<FoodItemImageDTO>> deleteFoodItemImageById(Integer foodItemImageId) {
        this.foodItemImageController.deleteImageById(foodItemImageId, newFoodItemDatabase());
        return ResponseEntityModelUtil.OK(null);
    }

    private FoodItemImageRules newFoodItemImageRule() {
        return new FoodItemImageRules(foodItemConfig.getImage().getLocationPrefix(), foodItemConfig.getMaxImages(), foodItemConfig.getImage().getMaxSize(), foodItemConfig.getImage().getAllowedExtensions());
    }

    private FoodItemDatabase newFoodItemDatabase() {
        return new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
    }


}
