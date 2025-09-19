package br.com.tp.lncr.app.apis.fooditem;

import br.com.tp.lncr.app.commons.model.ResponseModel;
import br.com.tp.lncr.app.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lncr.app.configs.FoodItemConfig;
import br.com.tp.lncr.app.dataproxy.FoodItemDataProxy;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemImageController;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodItems/image")
public class FoodItemImageRestControllerImpl implements FoodItemImageRestController {

    private final FoodItemDataProxy foodItemDataProxy;
    private final FoodItemImageController foodItemImageController;
    private final FoodItemConfig foodItemConfig;

    public FoodItemImageRestControllerImpl(FoodItemDataProxy foodItemDataProxy, FoodItemImageController foodItemImageController, FoodItemConfig foodItemConfig) {
        this.foodItemDataProxy = foodItemDataProxy;
        this.foodItemImageController = foodItemImageController;
        this.foodItemConfig = foodItemConfig;
    }

    @Override
    @GetMapping("/{foodItemImageId}")
    public ResponseEntity<ResponseModel<FoodItemImageDTO>> getFoodItemImageById(@PathVariable(name = "foodItemImageId") Integer foodItemImageId) {
        FoodItemImageDTO foodItemImageDTO = this.foodItemImageController.getImageById(foodItemImageId, this.foodItemDataProxy, foodItemConfig.getImage().getLocationPrefix());
        return ResponseEntityModelUtil.OK(foodItemImageDTO);
    }

    @Override
    @PutMapping("/{foodItemImageId}")
    public ResponseEntity<ResponseModel<FoodItemImageDTO>> updateFoodItemImageById(@PathVariable(name = "foodItemImageId") Integer foodItemImageId, @RequestBody FoodItemImageDTO foodItemImageDTO) {
        foodItemImageDTO = this.foodItemImageController.updateImageById(foodItemImageId, foodItemImageDTO, this.foodItemDataProxy, newFoodItemImageRule());
        return ResponseEntityModelUtil.OK(foodItemImageDTO);
    }


    @Override
    @DeleteMapping("{foodItemImageId}")
    public ResponseEntity<ResponseModel<FoodItemImageDTO>> deleteFoodItemImageById(Integer foodItemImageId) {
        this.foodItemImageController.deleteImageById(foodItemImageId, this.foodItemDataProxy);
        return ResponseEntityModelUtil.OK(null);
    }

    private FoodItemImageRules newFoodItemImageRule() {
        return new FoodItemImageRules(foodItemConfig.getImage().getLocationPrefix(), foodItemConfig.getMaxImages(), foodItemConfig.getImage().getMaxSize(), foodItemConfig.getImage().getAllowedExtensions());
    }

}
