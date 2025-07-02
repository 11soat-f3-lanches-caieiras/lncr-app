package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;

public class FoodItemImagePresenter {
    public FoodItemImageDTO created(FoodItemImageDTO foodItemImageDTO, FoodItemConfig foodItemConfig) {
        return formatFoodItemImageDTO(foodItemImageDTO,foodItemConfig.getImage().getLocationPrefix(),false);
    }

    public FoodItemImageDTO getById(FoodItemImageDTO foodItemImageDTO, FoodItemConfig foodItemConfig) {
        return formatFoodItemImageDTO(foodItemImageDTO,foodItemConfig.getImage().getLocationPrefix(),true);
    }

    private FoodItemImageDTO formatFoodItemImageDTO(FoodItemImageDTO foodItemImageDTO, String imgageLocationPrefix, Boolean includeData) {
        Integer id = foodItemImageDTO.getId();
        String location = imgageLocationPrefix + "/" + id;
        foodItemImageDTO.setId(id);
        foodItemImageDTO.setLocation(location);
        foodItemImageDTO.setFoodItemId(null);

        if (includeData == false) {
            foodItemImageDTO.set_data(null);
        }
        foodItemImageDTO.setFileName(null);
        foodItemImageDTO.setFileExtension(null);
        return foodItemImageDTO;
    }

}
