package br.com.tp.lanchescaieiras.commons.interfaces.foodItem;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface FoodItemImageRestController {

    ResponseEntity<Response<FoodItemImageDTO>> getFoodItemImageById(@PathVariable Integer foodItemImageId);

    ResponseEntity<Response<FoodItemImageDTO>> updateFoodItemImageById(@PathVariable Integer foodItemImageId, @RequestBody FoodItemImageDTO foodItemImageDTO);

    ResponseEntity<Response<FoodItemImageDTO>> deleteFoodItemImageById(@PathVariable Integer foodItemImageId, @RequestBody FoodItemImageDTO foodItemImageDTO);



}
