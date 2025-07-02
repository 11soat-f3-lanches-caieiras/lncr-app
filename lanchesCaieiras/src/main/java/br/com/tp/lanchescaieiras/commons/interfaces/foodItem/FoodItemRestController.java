package br.com.tp.lanchescaieiras.commons.interfaces.foodItem;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface FoodItemRestController {

    ResponseEntity<Response<FoodItemDTO>> createFoodItem(@RequestBody FoodItemDTO foodItemDTO);

    ResponseEntity<ResponseList<FoodItemDTO>> getAllFoodItems(@RequestParam(name = "_limit", required = false) Integer _limit,
                                                              @RequestParam(name = "category", required = false) String category,
                                                              @RequestParam(name = "includeImages", required = false) Boolean includeImages);

    ResponseEntity<Response<FoodItemDTO>> getFoodItemById(@PathVariable(name = "id") Integer foodItemId,
                                                          @RequestParam(name = "includeImages", required = false) Boolean includeImages);

    ResponseEntity<Response<FoodItemDTO>> partialUpdateFoodItemById(@PathVariable(name = "foodItemId") Integer foodItemId,
                                                                    @RequestBody FoodItemDTO foodItemDTO);

    ResponseEntity<Response<FoodItemDTO>> deleteFoodItemById(@PathVariable Integer id);

    ResponseEntity<Response<FoodItemImageDTO>> createFoodItemImage(@PathVariable("foodItemId") Integer foodItemId, @RequestBody FoodItemImageDTO foodItemImageDTO);

    ResponseEntity<ResponseList<FoodItemImageDTO>> getFoodItemImagesByFoodItemId(@PathVariable Integer foodItemId, @RequestParam(name="includeData",required = false, defaultValue = "false") Boolean includeData);

    ResponseEntity<Response<FoodItemImageDTO>> deleteFoodItemImageByFoodItemId(@RequestParam("foodItemId") Integer foodItemId, FoodItemImageDTO foodItemImageDTO);
}
