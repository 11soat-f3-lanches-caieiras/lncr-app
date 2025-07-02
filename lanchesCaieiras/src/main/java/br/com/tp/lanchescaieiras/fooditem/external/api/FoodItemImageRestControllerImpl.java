package br.com.tp.lanchescaieiras.fooditem.external.api;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.commons.interfaces.foodItem.FoodItemImageRestController;
import br.com.tp.lanchescaieiras.commons.utils.ResponseEntityUtil;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemImageControllerImpl;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;
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
    public ResponseEntity<Response<FoodItemImageDTO>> getFoodItemImageById(@PathVariable(name = "foodItemImageId") Integer foodItemImageId) {
        FoodItemDataProxy foodItemDatabase = new FoodItemDataProxy(this.jpaFoodItemDatabase, this.jpaFoodItemImageDatabase, this.foodItemImageStorage);
        FoodItemImageDTO foodItemImageDTO =  this.foodItemImageController.getImageById(foodItemImageId, foodItemDatabase, this.foodItemConfig);
        return ResponseEntityUtil.OK(foodItemImageDTO);
    }

    @Override
    @PutMapping("/{foodItemImageId}")
    public ResponseEntity<Response<FoodItemImageDTO>> updateFoodItemImageById(@PathVariable(name = "foodItemImageId") Integer foodItemImageId, @RequestBody FoodItemImageDTO foodItemImageDTO) {
        return null;
    }

    @Override
    @DeleteMapping("{foodItemImageId}")
    public ResponseEntity<Response<FoodItemImageDTO>> deleteFoodItemImageById(Integer foodItemImageId, FoodItemImageDTO foodItemImageDTO) {
        return null;
    }




}
