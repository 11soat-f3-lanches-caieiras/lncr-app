package br.com.tp.lncr.app.datasources.postgres.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.enums.FoodItemCategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaFoodItemMapper {
    public JpaFoodItemEntity foodItemDtoToJpa(FoodItemDTO foodItemDTO) {
        if (foodItemDTO == null) return null;
        JpaFoodItemEntity entity = new JpaFoodItemEntity();
        entity.setId(foodItemDTO.getId());
        entity.setName(foodItemDTO.getName());
        entity.setDescription(foodItemDTO.getDescription());
        entity.setPrice(foodItemDTO.getPrice());
        entity.setCategoryId(FoodItemCategory.fromDescription(foodItemDTO.getCategory()).getId());
        return entity;
    }

    public FoodItemDTO jpaFoodItemToDTO(JpaFoodItemEntity entity) {
        if (entity == null) return null;
        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setCategory(FoodItemCategory.fromId(entity.getCategoryId()).getDescription());
        return dto;
    }

    public JpaFoodItemImageEntity foodItemImageDtoToJpa(FoodItemImageDTO dto) {
        if (dto == null) return null;
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        entity.id = dto.getId();
        entity.foodItemId = dto.getFoodItemId();
        entity._data = dto.get_data();
        entity.location = dto.getLocation();
        entity.fileName = dto.getFileName();
        entity.fileExtension = dto.getFileExtension();
        entity.imageError = dto.getImageError();

        return entity;
    }

    public FoodItemImageDTO jpaFoodItemImageToDTO(JpaFoodItemImageEntity entity) {
        if (entity == null) return null;
        FoodItemImageDTO dto = new FoodItemImageDTO();
        dto.setId(entity.getId());
        dto.setFoodItemId(entity.getFoodItemId());
        dto.set_data(entity._data);
        dto.setFileName(entity.fileName);
        dto.setFileExtension(entity.fileExtension);
        dto.setImageError(entity.imageError);
        return dto;
    }

    public List<FoodItemImageDTO> jpaFoodItemImageToDtoList(List<JpaFoodItemImageEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::jpaFoodItemImageToDTO).collect(Collectors.toList());
    }

    public List<JpaFoodItemImageEntity> foodItemImageDtoToJpaList(List<FoodItemImageDTO> foodItemImageDTOS) {
        if (foodItemImageDTOS == null) return null;
        return foodItemImageDTOS.stream().map(this::foodItemImageDtoToJpa).collect(Collectors.toList());
    }
}
