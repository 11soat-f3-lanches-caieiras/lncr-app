package br.com.tp.lanchescaieiras._external.apis.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._external.commons.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface FoodItemImageRestController {

    ResponseEntity<Response<FoodItemImageDTO>> getFoodItemImageById(@PathVariable Integer foodItemImageId);

    ResponseEntity<Response<FoodItemImageDTO>> updateFoodItemImageById(@PathVariable Integer foodItemImageId, @RequestBody FoodItemImageDTO foodItemImageDTO);

    ResponseEntity<Response<FoodItemImageDTO>> deleteFoodItemImageById(@PathVariable Integer foodItemImageId);
}
