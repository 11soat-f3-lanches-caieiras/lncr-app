package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;

import java.util.List;

public class FoodItemPresenter {

    public FoodItemDTO created(FoodItemDTO foodItemDTO, FoodItemConfig foodItemConfig) {
        return formatFoodItemDTO(foodItemDTO,foodItemConfig.getImage().getLocationPrefix());
    }

    public List<FoodItemDTO> getAll(List<FoodItemDTO> foodItemListDTO, FoodItemConfig foodItemConfig) {
        return formatFoodItemDTOList(foodItemListDTO, foodItemConfig.getImage().getLocationPrefix());
    }

    public FoodItemDTO getById(FoodItemDTO foodItemDTO, FoodItemConfig foodItemConfig) {
        return formatFoodItemDTO(foodItemDTO, foodItemConfig.getImage().getLocationPrefix());
    }

    public FoodItemDTO patialUpdateById(FoodItemDTO foodItemDTO, FoodItemConfig foodItemConfig) {
        return formatFoodItemDTO(foodItemDTO, foodItemConfig.getImage().getLocationPrefix());
    }

    private List<FoodItemDTO> formatFoodItemDTOList(List<FoodItemDTO> foodItemListDTO, String foodItemLocation) {
        foodItemListDTO.forEach(foodItemDto -> {
            formatFoodItemDTO(foodItemDto, foodItemLocation);
        });
        return foodItemListDTO;
    }

    private FoodItemDTO formatFoodItemDTO(FoodItemDTO foodItemDTO, String imgageLocationPrefix) {
        if (foodItemDTO.getImages() != null) {
            foodItemDTO.getImages().forEach(img -> {
                if (img.getId() != null && img.getImageError() == null) {
                    img.setFoodItemId(foodItemDTO.getId());
                    Integer id = img.getId();
                    String location = imgageLocationPrefix + "/" + id;
                    img.setId(id);
                    img.setLocation(location);
                }
                img.setFoodItemId(null);
                img.set_data(null);
                img.setFileName(null);
                img.setFileExtension(null);
            });
        }
        return foodItemDTO;
    }

}
