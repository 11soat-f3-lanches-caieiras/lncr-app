package br.com.tp.lanchescaieiras.fooditem.mappers;

import br.com.tp.lanchescaieiras.commons.domain.Image;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FoodItemImageMapper {

    @Mappings({
            @Mapping(target = "id", expression = "java(new JpaFoodItemImageEntity().setImageId(index, foodItemId))"),
            @Mapping(target = "foodItemId", expression = "java(foodItemId)"),
            @Mapping(target = "fileName", expression = "java(image.getId() + \".\" + image.getFileExtension())")})
    JpaFoodItemImageEntity domainToJpa(Image image, Integer foodItemId, int index);

    @Mappings({
            @Mapping(target = "id", source = "jpaFoodItemImageEntity.id"),
            @Mapping(target = "fileName", source = "jpaFoodItemImageEntity.fileName"),
            @Mapping(target = "fileExtension", ignore = true),
            @Mapping(target = "location", expression = "java(jpaFoodItemImageEntity.setLocation(jpaFoodItemImageEntity.getImagePath(), jpaFoodItemImageEntity.getId()))")})
    Image jpaToDomain(JpaFoodItemImageEntity jpaFoodItemImageEntity);

}
