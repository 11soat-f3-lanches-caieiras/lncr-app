package br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemCategory;

import java.util.List;
import java.util.stream.Collectors;

public class JpaFoodItemPostgresMapper {
    public JpaFoodItemPostgresEntity toJpaFoodItemPostgresEntity(FoodItemDTO foodItemDTO) {
        if (foodItemDTO == null) return null;
        JpaFoodItemPostgresEntity entity = new JpaFoodItemPostgresEntity();
        entity.setId(foodItemDTO.getId());
        entity.setName(foodItemDTO.getName());
        entity.setDescription(foodItemDTO.getDescription());
        entity.setPrice(foodItemDTO.getPrice());
        entity.setCategoryId(FoodItemCategory.fromDescription(foodItemDTO.getCategory()).getId());
        return entity;
    }

    public FoodItemDTO toFoodItemDTO(JpaFoodItemPostgresEntity entity) {
        if (entity == null) return null;
        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setCategory(FoodItemCategory.fromId(entity.getCategoryId()).getDescription());
        return dto;
    }

    public JpaFoodItemImagePostgresEntity toJpaFoodItemImageEntity(FoodItemImageDTO dto) {
        if (dto == null) return null;
        JpaFoodItemImagePostgresEntity entity = new JpaFoodItemImagePostgresEntity();
        entity.id = dto.getId();
        entity.foodItemId = dto.getFoodItemId();
        entity._data = dto.get_data();
        entity.location = dto.getLocation();
        entity.fileName = dto.getFileName();
        entity.fileExtension = dto.getFileExtension();
        entity.imageError = dto.getImageError();

        return entity;
    }

    public FoodItemImageDTO toFoodItemImageDTO(JpaFoodItemImagePostgresEntity entity) {
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

    public List<FoodItemImageDTO> toFoodItemImageDTOList(List<JpaFoodItemImagePostgresEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toFoodItemImageDTO).collect(Collectors.toList());
    }

    public List<JpaFoodItemImagePostgresEntity> toJpaFoodItemImageEntityList(List<FoodItemImageDTO> foodItemImageDTOS) {
        if (foodItemImageDTOS == null) return null;
        return foodItemImageDTOS.stream().map(this::toJpaFoodItemImageEntity).collect(Collectors.toList());
    }
}
