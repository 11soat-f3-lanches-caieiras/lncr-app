package br.com.tp.lanchescaieiras._core.adapters.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItem;
import br.com.tp.lanchescaieiras._core.commons.enums.FoodItemCategory;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;

import java.util.List;
import java.util.stream.Collectors;

public class FoodItemMapper {

    public FoodItemDTO domainToDto(FoodItem foodItem) {
        if (foodItem == null) return null;
        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(foodItem.getId());
        dto.setName(foodItem.getName());
        dto.setDescription(foodItem.getDescription());
        dto.setPrice(foodItem.getPrice());
        dto.setCategory(foodItem.getCategory() != null ? foodItem.getCategory().getDescription() : null);
        dto.setImages(imageDomainListToDtoList(foodItem.getImages()));
        return dto;
    }

    public FoodItem dtoToDomain(FoodItemDTO foodItemDTO) {
        if (foodItemDTO == null) return null;
        FoodItem domain = new FoodItem();
        domain.setId(foodItemDTO.getId());
        domain.setName(foodItemDTO.getName());
        domain.setDescription(foodItemDTO.getDescription());
        domain.setPrice(foodItemDTO.getPrice());
        domain.setCategory(FoodItemCategory.fromDescription(foodItemDTO.getCategory()));
        domain.setImages(imageDtoListToDomainList(foodItemDTO.getImages()));
        return domain;
    }

    public FoodItemImageDTO imageDomainToDto(FoodItemImage image) {
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

    public FoodItemImage imageDtoToDomain(FoodItemImageDTO dto) {
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

    public List<FoodItemImageDTO> imageDomainListToDtoList(List<FoodItemImage> images) {
        if (images == null) return null;
        return images.stream().map(this::imageDomainToDto).collect(Collectors.toList());
    }

    public List<FoodItemImage> imageDtoListToDomainList(List<FoodItemImageDTO> dtos) {
        if (dtos == null) return null;
        return dtos.stream().map(this::imageDtoToDomain).collect(Collectors.toList());
    }
}
