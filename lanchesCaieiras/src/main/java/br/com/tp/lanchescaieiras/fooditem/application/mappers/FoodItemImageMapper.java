package br.com.tp.lanchescaieiras.fooditem.application.mappers;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FoodItemImageMapper {

    @Mappings({
            @Mapping(target = "id", expression = "java(new JpaFoodItemImageEntity().setImageId(index, foodItemId))"),
            @Mapping(target = "foodItemId", expression = "java(foodItemId)"),
            @Mapping(target = "fileExtension", source = "foodItemImage.fileExtension"),
            @Mapping(target = "_data", source = "foodItemImage._data"),
            @Mapping(target = "location", ignore = true),
            @Mapping(target = "fileName", expression = "java(new JpaFoodItemImageEntity().setImageId(index, foodItemId) + \".\" + foodItemImage.getFileExtension())")})
    JpaFoodItemImageEntity domainToJpa(FoodItemImage foodItemImage, Integer foodItemId, int index);

    @Mappings({
            @Mapping(target = "id", source = "jpaFoodItemImageEntity.id"),
            @Mapping(target = "fileName", ignore = true),
            @Mapping(target = "fileExtension", ignore = true),
            @Mapping(target = "_data", ignore = true),
            @Mapping(target = "location", source = "jpaFoodItemImageEntity.location"),})
    FoodItemImage jpaToDomain(JpaFoodItemImageEntity jpaFoodItemImageEntity);



    @Mapping(target = "_data", source = "jpaFoodItemImageEntity._data")
    FoodItemImage jpaToImageData(JpaFoodItemImageEntity jpaFoodItemImageEntity);
}
