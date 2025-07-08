package br.com.tp.lanchescaieiras._external.apis.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface FoodItemImageRestController {

    ResponseEntity<ResponseModel<FoodItemImageDTO>> getFoodItemImageById(@PathVariable Integer foodItemImageId);

    ResponseEntity<ResponseModel<FoodItemImageDTO>> updateFoodItemImageById(@PathVariable Integer foodItemImageId, @RequestBody FoodItemImageDTO foodItemImageDTO);

    ResponseEntity<ResponseModel<FoodItemImageDTO>> deleteFoodItemImageById(@PathVariable Integer foodItemImageId);
}
