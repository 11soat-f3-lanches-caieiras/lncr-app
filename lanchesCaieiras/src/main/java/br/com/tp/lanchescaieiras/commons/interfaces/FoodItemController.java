package br.com.tp.lanchescaieiras.commons.interfaces;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.external.FoodItemDataProxy;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FoodItemController {

    FoodItemDTO create(FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper);

    List<FoodItemDTO> getAll(Integer _limit, String category, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper);

    FoodItemDTO getById(Integer foodItemId, Boolean includeImages, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper);

    FoodItemDTO partialUpdateById(Integer id, FoodItemDTO foodItemDTO, FoodItemDatabase foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper);

    void deleteById(Integer foodItemId, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper);

    FoodItemImageDTO create(FoodItemImageDTO foodItemImageDTO, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper);

    FoodItemImageDTO getImageById(Integer foodItemImageId, FoodItemDataProxy foodItemDatabase, FoodItemConfig foodItemConfig, FoodItemMapper foodItemMapper);


}
