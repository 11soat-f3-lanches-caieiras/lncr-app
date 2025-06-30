package br.com.tp.lanchescaieiras.commons.interfaces;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface FoodItemRestController {

    ResponseEntity<Response<FoodItemDTO>> createFoodItem(@RequestBody FoodItemDTO foodItemDTO);

    ResponseEntity<ResponseList<FoodItemDTO>> getAllFoodItems(@RequestParam(name = "_limit", required = false) Integer _limit,
                                                              @RequestParam(name = "category", required = false) String category,
                                                              @RequestParam(name = "includeImages", required = false) Boolean includeImages);

    ResponseEntity<Response<FoodItemDTO>> getFoodItemById(@PathVariable("id") Integer foodItemId);

    ResponseEntity<Response<FoodItemDTO>> partialUpdateFoodItemById(@PathVariable Integer id, @RequestBody FoodItemDTO foodItemDTO);

    ResponseEntity<Response<FoodItemDTO>> deleteFoodItemById(@PathVariable Integer id);

    ResponseEntity<Response<FoodItemImageDTO>> getImageData(@PathVariable Integer id);

    ResponseEntity<Response<FoodItemImageDTO>> createImage(@PathVariable Integer foodItemId, @RequestBody FoodItemImageDTO foodItemImageDTO);

    ResponseEntity<Response<FoodItemImageDTO>> updateImageById(@PathVariable Integer id, @RequestBody FoodItemImageDTO foodItemImageDTO);
}
