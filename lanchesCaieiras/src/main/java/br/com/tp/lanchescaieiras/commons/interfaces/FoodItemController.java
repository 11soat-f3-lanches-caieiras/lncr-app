package br.com.tp.lanchescaieiras.commons.interfaces;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface FoodItemController {

    ResponseEntity<Response<FoodItemDTO>> create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper);

    ResponseEntity<ResponseList<FoodItemDTO>> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper);

  /*  ResponseEntity<ResponseList<FoodItemDTO>> getAllFoodItems(Optional<Integer> _limit, Optional<String> category);

    ResponseEntity<Response<FoodItemDTO>> getFoodItemById(@PathVariable("id") Integer foodItemId);

    ResponseEntity<Response<FoodItemDTO>> partialUpdateFoodItemById(@PathVariable Integer id, @RequestBody FoodItemDTO foodItemDTO);

    ResponseEntity<Response<FoodItemDTO>> deleteFoodItemById(@PathVariable Integer id);

    ResponseEntity<Response<FoodItemImageDTO>> getImageData(@PathVariable Integer id);

    ResponseEntity<Response<FoodItemImageDTO>> createImage(@PathVariable Integer foodItemId, @RequestBody FoodItemImageDTO foodItemImageDTO);

    ResponseEntity<Response<FoodItemImageDTO>> updateImageById(@PathVariable Integer id, @RequestBody FoodItemImageDTO foodItemImageDTO);
*/
}
