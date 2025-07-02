package br.com.tp.lanchescaieiras.fooditem.adapters;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;

import java.util.List;

public class FoodItemImagePresenter {
    public FoodItemImageDTO created(FoodItemImageDTO foodItemImageDTO, FoodItemConfig foodItemConfig) {
        return formatFoodItemImageDTO(foodItemImageDTO,foodItemConfig.getImage().getLocationPrefix(),false);
    }

    public FoodItemImageDTO getById(FoodItemImageDTO foodItemImageDTO, FoodItemConfig foodItemConfig) {
        return formatFoodItemImageDTO(foodItemImageDTO,foodItemConfig.getImage().getLocationPrefix(),true);
    }

    private FoodItemImageDTO formatFoodItemImageDTO(FoodItemImageDTO foodItemImageDTO, String imageLocationPrefix, Boolean includeData) {
        Integer id = foodItemImageDTO.getId();
        String location =  imageLocationPrefix + "/" + id;
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

    private List<FoodItemImageDTO> foodItemImageDTOList(List<FoodItemImageDTO> foodItemImageDTOList, String imageLocationPrefix, Boolean includeData){
        for(FoodItemImageDTO image : foodItemImageDTOList){ image = formatFoodItemImageDTO(image,imageLocationPrefix,includeData);}
        return foodItemImageDTOList;
    }

    public List<FoodItemImageDTO> getAllImagesByFoodItemId(List<FoodItemImageDTO> foodItemImageDTOList, String imageLocationPrefix, Boolean includeData) {
        return foodItemImageDTOList(foodItemImageDTOList, imageLocationPrefix,includeData);
    }
}
