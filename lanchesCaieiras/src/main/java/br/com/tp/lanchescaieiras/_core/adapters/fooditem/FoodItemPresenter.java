package br.com.tp.lanchescaieiras._core.adapters.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;

import java.util.List;

public class FoodItemPresenter {

    private final FoodItemMapper foodItemMapper;

    public FoodItemPresenter(FoodItemMapper foodItemMapper) {
        this.foodItemMapper = foodItemMapper;
    }

    public FoodItemDTO created(FoodItem foodItem, String imageLocationPrefix) {
        return formatFoodItemDTO(foodItemMapper.domainToDto(foodItem), imageLocationPrefix);
    }

    public List<FoodItemDTO> getAll(List<FoodItem> foodItemList, String imageLocationPrefix) {
        return formatFoodItemDTOList(foodItemList.stream().map(foodItemMapper::domainToDto).toList(), imageLocationPrefix);
    }

    public FoodItemDTO getById(FoodItem foodItem, String imageLocationPrefix) {
        return formatFoodItemDTO(foodItemMapper.domainToDto(foodItem), imageLocationPrefix);
    }

    public FoodItemDTO patialUpdateById(FoodItem foodItem, String imageLocationPrefix) {
        return formatFoodItemDTO(foodItemMapper.domainToDto(foodItem), imageLocationPrefix);
    }

    private List<FoodItemDTO> formatFoodItemDTOList(List<FoodItemDTO> foodItemListDTO, String imageLocationPrefix) {
        foodItemListDTO.forEach(foodItemDto -> {
            formatFoodItemDTO(foodItemDto, imageLocationPrefix);
        });
        return foodItemListDTO;
    }

    private FoodItemDTO formatFoodItemDTO(FoodItemDTO foodItemDTO, String imageLocationPrefix) {
        if (foodItemDTO.getImages() != null) {
            foodItemDTO.getImages().forEach(img -> {
                if (img.getId() != null && img.getImageError() == null) {
                    img.setFoodItemId(foodItemDTO.getId());
                    Integer id = img.getId();
                    String location = imageLocationPrefix + "/" + id;
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
