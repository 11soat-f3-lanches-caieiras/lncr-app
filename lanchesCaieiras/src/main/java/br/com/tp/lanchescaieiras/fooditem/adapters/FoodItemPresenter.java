package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FoodItemPresenter {

   public ResponseEntity<Response<FoodItemDTO>> created(FoodItemDTO foodItemDTO, FoodItemConfig foodItemConfig) {
        if (foodItemDTO.getImages() != null) {
            foodItemDTO.getImages().forEach(img -> {
                if (img.getId() != null && img.getImageError() == null){
                    img.setFoodItemId(foodItemDTO.getId());
                    Integer id = img.getId();
                    String location = foodItemConfig.getImage().getLocationPrefix() + "/" + id;
                    img.setId(id);
                    img.setLocation(location);
                }
                img.setFoodItemId(null);
                img.set_data(null);
                img.setFileName(null);
                img.setFileExtension(null);
            });
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", foodItemConfig.getLocationPrefix() + "/" + foodItemDTO.getId())
                .body(new Response<>(foodItemDTO));
    }

    public ResponseEntity<ResponseList<FoodItemDTO>> getAll(List<FoodItemDTO> foodItemListDTO, FoodItemConfig foodItemConfig) {
       foodItemListDTO.forEach(foodItemDto -> {
           if (foodItemDto.getImages() != null) {
                foodItemDto.getImages().forEach(img -> {
                    if (img.getId() != null && img.getImageError() == null) {
                        String location = foodItemConfig.getImage().getLocationPrefix() + "/" + img.getId();
                        img.setId(img.getId());
                        img.setLocation(location);
                    }

                });
            }
        });
        return ResponseEntity.ok(new ResponseList<>(foodItemListDTO));
    }

    public ResponseEntity<Response<FoodItemDTO>> getById(FoodItemDTO foodItemDTO, FoodItemConfig foodItemConfig) {
        if (foodItemDTO.getImages() != null) {
            foodItemDTO.getImages().forEach(img -> {
                if (img.getId() != null && img.getImageError() == null) {
                    img.setFoodItemId(foodItemDTO.getId());
                    Integer id = img.getId();
                    String location = foodItemConfig.getImage().getLocationPrefix() + "/" + id;
                    img.setId(id);
                    img.setLocation(location);
                }
                img.setFoodItemId(null);
                img.set_data(null);
                img.setFileName(null);
                img.setFileExtension(null);
            });
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(foodItemDTO));
    }
}
