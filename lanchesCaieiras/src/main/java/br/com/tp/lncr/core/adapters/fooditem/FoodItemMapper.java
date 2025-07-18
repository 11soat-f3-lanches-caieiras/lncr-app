package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;

import java.util.List;
import java.util.stream.Collectors;

public class FoodItemMapper {

    public FoodItemDTO foodItemToDTO(FoodItem foodItem) {
        if (foodItem == null) return null;
        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(foodItem.getId());
        dto.setName(foodItem.getName());
        dto.setDescription(foodItem.getDescription());
        dto.setPrice(foodItem.getPrice());
        dto.setCategory(foodItem.getCategory() != null ? foodItem.getCategory().getDescription() : null);
        dto.setImages(foodItemImageToDtoList(foodItem.getImages()));
        return dto;
    }

    public FoodItem foodItemToDomain(FoodItemDTO foodItemDTO) {
        if (foodItemDTO == null) return null;
        FoodItem domain = new FoodItem();
        domain.setId(foodItemDTO.getId());
        domain.setName(foodItemDTO.getName());
        domain.setDescription(foodItemDTO.getDescription());
        domain.setPrice(foodItemDTO.getPrice());
        domain.setCategory(FoodItemCategory.fromDescription(foodItemDTO.getCategory()));
        domain.setImages(foodItemImageToDomainList(foodItemDTO.getImages()));
        return domain;
    }

    public FoodItemImageDTO foodItemImageToDTO(FoodItemImage image) {
        if (image == null) return null;
        FoodItemImageDTO dto = new FoodItemImageDTO();
        dto.setId(image.getId());
        dto.setFoodItemId(image.getFoodItemId());
        dto.set_data(image.get_data());
        dto.setFileName(image.getFileName());
        dto.setFileExtension(image.getFileExtension());
        dto.setImageError(image.getImageError());
        dto.setLocation(image.getLocation());

        return dto;
    }

    public FoodItemImage foodItemImageToDomain(FoodItemImageDTO dto) {
        if (dto == null) return null;
        FoodItemImage image = new FoodItemImage();
        image.setId(dto.getId());
        image.setFoodItemId(dto.getFoodItemId());
        image.set_data(dto.get_data());
        image.setLocation(dto.getLocation());
        image.setFileName(dto.getFileName());
        image.setFileExtension(dto.getFileExtension());
        image.setImageError(dto.getImageError());
        return image;
    }

    public List<FoodItemImageDTO> foodItemImageToDtoList(List<FoodItemImage> images) {
        if (images == null) return null;
        return images.stream().map(this::foodItemImageToDTO).collect(Collectors.toList());
    }

    public List<FoodItemImage> foodItemImageToDomainList(List<FoodItemImageDTO> dtos) {
        if (dtos == null) return null;
        return dtos.stream().map(this::foodItemImageToDomain).collect(Collectors.toList());
    }
}
